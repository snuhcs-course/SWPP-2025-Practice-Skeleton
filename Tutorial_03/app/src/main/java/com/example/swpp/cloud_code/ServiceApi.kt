package com.example.swpp.cloud_code

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {
    @POST("/user/write")
    fun userWrite(@Body data: NameIdData): Call<CodeMessageResponse>
}
