package com.example.app.utils

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.openai.com/v1/"

    private val moshi: Moshi = Moshi.Builder().build() // ✅ Ensure Moshi is initialized

    fun create(apiKey: String): OpenAiApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OpenAiOkHttpClient.getClient(apiKey))
            .build()
            .create(OpenAiApiService::class.java)
    }
}
