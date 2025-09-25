package com.example.swpp.cloud_code

import com.google.gson.annotations.SerializedName

data class CodeMessageResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String
)
