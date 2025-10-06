# urls.py
from django.urls import path
from . import views
from rest_framework import generics
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

urlpatterns = [
    path("todos/", views.TodoListCreateView.as_view()),
    path("todos/<int:pk>/", views.TodoRetrieveUpdateView.as_view()),
    path("todos/<int:pk>/verify/", views.verify_evidence),  # Using pk for consistency
    path("uploads/presign/", views.create_presigned_url),  # S3 upload
    path("evidences/<int:pk>/", views.EvidenceDetailView.as_view()),
    path("generate_evidence/", views.generate_evidence),
]

urlpatterns += [
    path("api/token/", TokenObtainPairView.as_view(), name="token_obtain_pair"),
    path("api/token/refresh/", TokenRefreshView.as_view(), name="token_refresh"),
]