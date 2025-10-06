import boto3
from moto import mock_aws
from django.test import TestCase
from rest_framework.test import APIRequestFactory
from rest_framework import status
from django.conf import settings
import json
import logging

# Set up logging
logger = logging.getLogger(__name__)

@mock_aws
class AWSTestCase(TestCase):
    def setUp(self):
        # Set up logging
        logging.basicConfig(level=logging.DEBUG)
        
        # Create a mock S3 bucket
        self.s3 = boto3.client('s3', 
                             aws_access_key_id='test',
                             aws_secret_access_key='test',
                             region_name='us-east-1')
        self.bucket_name = 'test-bucket'
        self.s3.create_bucket(Bucket=self.bucket_name)
        
        # Configure settings for testing
        settings.USE_AWS = True
        settings.AWS_STORAGE_BUCKET_NAME = self.bucket_name
        settings.AWS_ACCESS_KEY_ID = 'test'
        settings.AWS_SECRET_ACCESS_KEY = 'test'
        settings.AWS_S3_REGION_NAME = 'us-east-1'
        
        # Set up request factory
        self.factory = APIRequestFactory()

    def test_presigned_url_creation(self):
        """Test that a presigned URL can be generated."""
        # Create a POST request with data
        data = {
            'file_name': 'test.jpg',
            'file_type': 'image/jpeg'
        }
        
        try:
            request = self.factory.post(
                '/uploads/presign/',
                data=json.dumps(data),
                content_type='application/json'
            )
            
            # Import the view here to ensure settings are properly configured
            from .views import create_presigned_url
            
            # Call the view
            response = create_presigned_url(request)
            
            # Log the response for debugging
            logger.debug(f"Response: {response.data}")
            
            # Check the response status code
            self.assertEqual(response.status_code, status.HTTP_200_OK)
            
            # Check the response structure
            response_data = response.data
            self.assertIn('key', response_data)
            self.assertIn('presigned', response_data)
            
            # Check the presigned URL structure
            presigned = response_data['presigned']
            self.assertIn('url', presigned)
            self.assertIn('fields', presigned)
            
            # Check the fields
            fields = presigned['fields']
            self.assertIn('key', fields)
            self.assertIn('Content-Type', fields)
            self.assertIn('AWSAccessKeyId', fields)
            self.assertIn('policy', fields)
            self.assertIn('signature', fields)
            
        except Exception as e:
            logger.exception("Test failed with exception:")
            raise