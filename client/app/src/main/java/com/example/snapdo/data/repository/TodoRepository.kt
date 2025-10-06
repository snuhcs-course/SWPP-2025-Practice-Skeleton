package com.example.snapdo.data.repository

import android.util.Log
import com.example.snapdo.data.model.Todo
import com.example.snapdo.data.network.GenerateEvidenceRequest
import com.example.snapdo.data.network.RetrofitInstance
import com.example.snapdo.data.network.TodoRequest

class TodoRepository {
    suspend fun fetchTodos(): List<Todo> {
        return RetrofitInstance.api.getTodos()
    }

    suspend fun addTodo(title: String, description: String?, evidence: String): Todo {
        val request = TodoRequest(title, description, evidence)
        return RetrofitInstance.api.addTodo(request) // POST call
    }

    suspend fun generateEvidence(title: String, description: String?): String {
        val response = RetrofitInstance.api.generateEvidence(
            GenerateEvidenceRequest(title, description)
        )
        return response.evidence
    }
}

