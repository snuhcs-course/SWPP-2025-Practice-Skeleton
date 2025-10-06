package com.example.snapdo.data.network

import com.example.snapdo.data.model.Todo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class TodoRequest(
    val title: String,
    val description: String?,
    val target_evidence: String
)

data class VerifyRequest(
    val image_base64: String
)

data class VerifyResponse(
    val verdict: String,
    val confidence: Float,
    val explanation: String
)

data class GenerateEvidenceRequest(
    val title: String,
    val description: String? = null
)

data class GenerateEvidenceResponse(
    val evidence: String
)



interface ApiService {
    @GET("snapdo/todos/")
    suspend fun getTodos(): List<Todo>


    @POST("snapdo/todos/")
    suspend fun addTodo(@Body todo: TodoRequest): Todo

    @POST("snapdo/todos/{id}/verify/")
    suspend fun verifyTask(
        @Path("id") taskId: Int,
        @Body request: VerifyRequest
    ): VerifyResponse

    @POST("snapdo/generate_evidence/")
    suspend fun generateEvidence(
        @Body request: GenerateEvidenceRequest
    ): GenerateEvidenceResponse

}
