from langchain_openai import ChatOpenAI
from langchain_core.prompts import PromptTemplate
from langchain_openai import ChatOpenAI
from pydantic import BaseModel, Field
from dotenv import load_dotenv

load_dotenv()

class AnalysisResult(BaseModel):
    # TODO

prompt = # TODO
llm = # TODO
chain = prompt | llm
result = # TODO

print(result.emotion)
print(result.intensity)
