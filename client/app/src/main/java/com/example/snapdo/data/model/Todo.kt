package com.example.snapdo.data.model

data class Todo(
    val id: Int,
    val title: String,
    val description: String?,
    val target_evidence: String,
    val is_done: Boolean,
    val verdict: String? = null
)
