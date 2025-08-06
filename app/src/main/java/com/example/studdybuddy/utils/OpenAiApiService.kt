package com.example.app.utils

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Response

interface OpenAiApiService {
    @Headers("Content-Type: application/json")
    @POST("responses")
    suspend fun getStudyPlan(
        @Body request: OpenAiRequest
    ): Response<OpenAiResponse>
}
