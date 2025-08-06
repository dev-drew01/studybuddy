package com.example.app.utils

object OpenAiApiClient {
    // Replace with your actual API key or use a secure source
    private const val API_KEY = "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"

    val apiService: OpenAiApiService by lazy {
        RetrofitClient.create(API_KEY)
    }
}
