# views.py
import os
import uuid
import base64
from django.conf import settings
from rest_framework.decorators import api_view, permission_classes, throttle_classes
from rest_framework.response import Response
from rest_framework import status
from rest_framework.throttling import ScopedRateThrottle
from .models import Todo, Evidence
from .serializers import TodoSerializer, TodoCreateSerializer, EvidenceSerializer
from rest_framework.permissions import AllowAny
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from rest_framework import generics, permissions
from django.http import Http404
import boto3
from django.contrib.auth import get_user_model
from .services.vlm_service import VLMService
import logging

from snapdo.services.vlm_service import *

logger = logging.getLogger(__name__)

class PresignThrottle(ScopedRateThrottle):
    scope = "presign"

class VerifyThrottle(ScopedRateThrottle):
    scope = "verify"

def save_uploaded_file(file, filename):
    """Save uploaded file to local storage"""
    os.makedirs(settings.MEDIA_ROOT, exist_ok=True)
    file_path = os.path.join(settings.MEDIA_ROOT, filename)
    with open(file_path, 'wb+') as destination:
        for chunk in file.chunks():
            destination.write(chunk)
    return file_path

def get_file_url(file_path):
    """Get file URL for local storage"""
    return f"{settings.MEDIA_URL}{os.path.basename(file_path)}"

@api_view(["POST"])
@permission_classes([AllowAny])
@throttle_classes([PresignThrottle])
def create_presigned_url(request):
    try:
        if settings.USE_AWS:
            # Handle S3 presigned URL generation (for direct S3 uploads)
            if request.content_type == 'application/json':
                # Handle JSON request (for S3 presigned URL)
                data = request.data
                filename = data.get("filename", f"{uuid.uuid4()}.jpg")
                content_type = data.get("content_type", "image/jpeg")
                key = f"mvp/{uuid.uuid4()}.jpg"
                
                s3 = boto3.client("s3", region_name=settings.AWS_S3_REGION_NAME)
                presigned = s3.generate_presigned_post(
                    Bucket=settings.AWS_STORAGE_BUCKET_NAME,
                    Key=key,
                    Fields={"Content-Type": content_type},
                    Conditions=[{"Content-Type": content_type}],
                    ExpiresIn=120,
                )
                return Response({"key": key, "presigned": presigned, "storage": "s3"})
            else:
                # Handle direct file upload (for local storage)
                if 'file' not in request.FILES:
                    return Response(
                        {"error": "No file provided"}, 
                        status=status.HTTP_400_BAD_REQUEST
                    )
                
                uploaded_file = request.FILES['file']
                filename = f"{uuid.uuid4()}_{uploaded_file.name}"
                file_path = save_uploaded_file(uploaded_file, filename)
                
                return Response({
                    "file_path": file_path,
                    "file_url": get_file_url(file_path),
                    "storage": "local"
                })
        else:
            # Local storage flow
            if 'file' in request.FILES:
                # Handle direct file upload
                uploaded_file = request.FILES['file']
                filename = f"{uuid.uuid4()}_{uploaded_file.name}"
                file_path = save_uploaded_file(uploaded_file, filename)
                
                return Response({
                    "file_path": file_path,
                    "file_url": get_file_url(file_path),
                    "storage": "local"
                })
            else:
                # Handle JSON request (for local storage with just metadata)
                data = request.data if hasattr(request, 'data') else {}
                filename = data.get("filename", f"{uuid.uuid4()}.jpg")
                return Response({
                    "message": "For local storage, please upload file directly using multipart/form-data",
                    "suggested_filename": filename
                })
                
    except Exception as e:
        return Response(
            {"error": str(e)}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["POST"])
@permission_classes([AllowAny])
@throttle_classes([VerifyThrottle])  # or create a separate GenerateThrottle if you prefer
def generate_evidence(request):
    """
    Generate a single-line, image-aware action item to help the user verify completion.
    Expected JSON:
    {
        "title": "Read 30 pages of a book",
        "description": "A photo of the book on page 31"
    }
    """
    try:
        # 1) Parse inputs
        title = request.data.get("title", "")
        description = request.data.get("description", "") # Optional

        if not title:
            return Response({"error": "title is required"}, status=status.HTTP_400_BAD_REQUEST)

        # 2) Call VLM service to generate a one-line action item
        try:
            vlm_service = VLMService()
            title_and_description = f"{title} - {description}".strip(" -")
            gen = vlm_service.generate_evidence(
                task_description=title_and_description
            )
            # Expected 'gen' example:
            # {
            #   "evidence":___
            # }
            return Response({
                "evidence": gen.get("evidence"),
                "title": title
            }, status=status.HTTP_200_OK)

        except Exception as e:
            logger.error(f"Error during VLM action generation: {str(e)}")
            return Response(
                {"error": f"Generation failed: {str(e)}"},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )

    except Exception as e:
        logger.exception("Error in generate_evidence")
        return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

@api_view(["POST"])
@permission_classes([AllowAny])
@throttle_classes([VerifyThrottle])
def verify_evidence(request, pk):
    """
    Verify evidence for a specific todo item using a base64 encoded image.
    Expected JSON: 
    {
        "image_base64": "data:image/jpeg;base64,/9j/4AAQSkZJRg..."
    }
    """
    try:
        # Get the todo item
        try:
            todo = Todo.objects.get(pk=pk)
        except Todo.DoesNotExist:
            return Response(
                {"error": "Todo not found"}, 
                status=status.HTTP_404_NOT_FOUND
            )
        
        # Get request data
        image_base64_full = request.data.get("image_base64")
        
        if not image_base64_full:
            return Response(
                {"error": "image_base64 is required"}, 
                status=status.HTTP_400_BAD_REQUEST
            )
        
        try:
            # Strip the header from the base64 string
            image_base64_data = image_base64_full
            if ',' in image_base64_full:
                header, image_base64_data = image_base64_full.split(',', 1)

            # Call VLM service to verify the evidence
            vlm_service = VLMService()
            vlm_result = vlm_service.verify_evidence(
                image_base64=image_base64_data,
                constraint=f"{todo.title} - {todo.target_evidence}"
            )
            
            # Create an evidence record
            evidence = Evidence.objects.create(
                todo=todo,
                s3_key="base64_image", # Placeholder since no file is saved
                result=vlm_result.get('verdict', 'PENDING'),
                score=vlm_result.get('confidence', 0.0),
                reason=vlm_result.get('explanation', '')
            )
            
            # Return the verification result
            return Response({
                "verdict": evidence.result,
                "score": evidence.score,
                "reason": evidence.reason,
                "evidence_id": evidence.id,
                "timestamp": evidence.submitted_at.isoformat()
            })
            
        except (ValueError, TypeError) as e:
            return Response({"error": f"Invalid base64 string provided: {e}"}, status=status.HTTP_400_BAD_REQUEST)
        except Exception as e:
            logger.error(f"Error during VLM verification: {str(e)}")
            return Response(
                {"error": f"Verification failed: {str(e)}"},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )
            
    except Exception as e:
        logger.exception("Error in verify_evidence")
        return Response(
            {"error": str(e)},
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )

# AllowAny
class TodoListCreateView(generics.ListCreateAPIView):
    queryset = Todo.objects.all()
    serializer_class = TodoSerializer
    permission_classes = [permissions.AllowAny]
    
    def perform_create(self, serializer):
        # For now, we'll use the first user as the owner
        # In a real app, you'd get this from the authenticated user
        User = get_user_model()
        
        # Get or create a default user
        default_user, _ = User.objects.get_or_create(
            username='default_user',
            defaults={'email': 'default@example.com', 'password': 'defaultpass123'}
        )
        
        serializer.save(owner=default_user)
    
    def get_queryset(self):
        # Return all todos (for testing)
        # In production, you'd filter by the current user
        return Todo.objects.all()

    def get_serializer_class(self):
        if self.request.method == "POST":
            return TodoCreateSerializer
        return TodoSerializer
    


class TodoRetrieveUpdateView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Todo.objects.all()
    serializer_class = TodoSerializer
    permission_classes = [permissions.AllowAny]  # 인증 없이 접근 허용
    
    def get_object(self):
        try:
            return Todo.objects.get(pk=self.kwargs["pk"])
        except Todo.DoesNotExist:
            raise Http404("Todo not found")

class EvidenceDetailView(generics.RetrieveUpdateAPIView):
    queryset = Evidence.objects.all()
    serializer_class = EvidenceSerializer
    permission_classes = [permissions.AllowAny]
    
    def get_object(self):
        return Evidence.objects.get(pk=self.kwargs["pk"])