package com.example.swpp.cloud_code

import com.google.gson.annotations.SerializedName

data class NameIdData(
    @SerializedName("userName")
    val userName: String,

    @SerializedName("userId")
    val userId: String
)
