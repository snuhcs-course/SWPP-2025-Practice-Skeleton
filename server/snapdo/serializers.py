# serializers.py (요지)
from rest_framework import serializers
from .models import Todo, Evidence

class TodoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Todo
        fields = ['id', 'title', 'description', 'target_evidence', 'is_done', 'created_at', 'owner']
        read_only_fields = ['owner']  # Make owner read-only in the API
        extra_kwargs = {
            'description': {'required': False, 'allow_blank': True},
            'target_evidence': {'required': False, 'allow_blank': True},
        }
    
    def to_representation(self, instance):
        ret = super().to_representation(instance)
        ret["owner"] = instance.owner.username
        return ret

    def create(self, validated_data):
        # The owner will be set in the view's perform_create
        return super().create(validated_data)

    

class TodoCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Todo
        fields = ["title", "description", "target_evidence"]
        extra_kwargs = {
            'description': {'required': False, 'allow_blank': True},
            'target_evidence': {'required': False, 'allow_blank': True},
        }

class EvidenceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Evidence
        fields = ["id", "todo", "s3_key", "result", "score", "reason"]
    
    def to_representation(self, instance):
        ret = super().to_representation(instance)
        ret["todo"] = instance.todo.target_evidence
        return ret