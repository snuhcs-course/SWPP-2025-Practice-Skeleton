package com.example.myapplication.ui.theme

import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

data class HeroRequest(val name: String, val age: Int, val score: Int)
data class CountResponse(val count: Int)
data class OkResponse(val ok: Boolean)

interface HeroApi {
    @POST("some_url - TODO")
    suspend fun createHero(@Body body: HeroRequest): OkResponse

    @GET("some_url - TODO")
    suspend fun getCount(): CountResponse
}

object ApiClient {
    // For emulator talking to host Django:
    private const val BASE_URL = "http://10.0.2.2:8000/"

    val api: HeroApi by lazy {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HeroApi::class.java)
    }
}