# models.py
from django.db import models
from django.contrib.auth.models import User

class Todo(models.Model):
    owner = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.CharField(max_length=200)
    description = models.TextField(blank=True)
    target_evidence = models.CharField(max_length=120, blank=True) 
    is_done = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        title = self.title or "No title"
        description = f" | {self.description}" if self.description else " | No description"
        return f"[{self.id}] {title}{description}"

class Evidence(models.Model):
    todo = models.ForeignKey(Todo, on_delete=models.CASCADE, related_name="evidences")
    s3_key = models.CharField(max_length=512)
    submitted_at = models.DateTimeField(auto_now_add=True)
    result = models.CharField(max_length=20, choices=[("PENDING","PENDING"),("PASSED","PASSED"),("FAILED","FAILED")], default="PENDING")
    score = models.FloatField(null=True, blank=True)
    reason = models.TextField(blank=True)
