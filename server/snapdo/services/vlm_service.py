import os
import requests
from typing import Dict, Any, Optional
from django.conf import settings
from langchain_openai import ChatOpenAI
from langchain_core.prompts import PromptTemplate
from langchain.prompts import ChatPromptTemplate
from pydantic import BaseModel, Field
import logging
from dotenv import load_dotenv

load_dotenv()

logger = logging.getLogger(__name__)

class VerificationResult(BaseModel):
    """Represents the result of the verification."""

    is_task_completed: bool = Field(
        description="True if the user in the photo has completed the task according to the constraints, False otherwise."
    )
    confidence: int = Field(
        description="Rate your confidence between 0.0~1.0"
    )
    explanation: str = Field(
        description="Describe about the result and interpretation"
    )

class EvidenceResult(BaseModel):
    
    evidence: str = Field(
        description="Describe actionable items given the goal user provided"
    )


class VLMService:
    """
    Service for handling Vision Language Model operations.
    """
    
    def __init__(self):
        """
        Initialize the VLM service.
        
        """
    
    def generate_evidence(self, task_description:str)-> Dict[str, Any]:
        if task_description=="":
            raise ValueError("Task description is required.")

        else: 
            # TODO: propmt
            prompt = None
            # TODO: llm that output with EvidenceResult
            llm = None
            # TODO: chain
            chain = None

            # run chain
            msg = chain.invoke({"task_description": task_description})
            print('evidence', msg.evidence)
        return {
                "evidence": msg.evidence
            }




    def verify_evidence(self, image_base64: str, constraint: str) -> Dict[str, Any]:
        try:
            if not image_base64:
                raise ValueError("image_base64 is required")

            # Construct the data URL for the LLM
            image_url = f"data:image/jpeg;base64,{image_base64}"

            # TODO: prompt
            prompt = None
            
            # TODO: llm that output with VerificationResult
            llm = None

            # TODO: chain
            chain = None

            print('VLM calling...')

            # TODO: Run chain with constraint(i.e., example) and image_url
            msg = chain.invoke(None)

            return {
                "verdict": "PASSED" if msg.is_task_completed else "FAILED",
                "confidence": msg.confidence,
                "explanation": msg.explanation
            }
            
        except Exception as e:
            logger.error(f"VLM API request failed: {str(e)}")
            raise Exception(f"Failed to verify evidence: {str(e)}")
    
    def get_verification_result(self, task_id: str) -> Dict[str, Any]:
        """
        Get the result of a verification task.
        
        Args:
            task_id: ID of the verification task
            
        Returns:
            Dict containing verification results
        """
        try:
            url = f"{self.api_url.rstrip('/')}/results/{task_id}"
            headers = {}
            if self.api_key:
                headers["Authorization"] = f"Bearer {self.api_key}"
                
            response = requests.get(url, headers=headers, timeout=self.timeout)
            response.raise_for_status()
            return response.json()
            
        except requests.exceptions.RequestException as e:
            logger.error(f"Failed to get verification result: {str(e)}")
            raise Exception(f"Failed to get verification result: {str(e)}")


