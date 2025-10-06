# permissions.py
import os
from rest_framework.permissions import BasePermission

class HasAPIKey(BasePermission):
    def has_permission(self, request, view):
        expected = os.environ.get("API_KEY")  # .env 등에 설정
        provided = request.headers.get("X-API-Key")
        return bool(expected and provided and provided == expected)
