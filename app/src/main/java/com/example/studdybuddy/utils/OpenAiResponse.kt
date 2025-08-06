// OpenAiResponse.kt
package com.example.app.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenAiResponse(
    val output: List<OutputMessage>
)

@JsonClass(generateAdapter = true)
data class OutputMessage(
    val id: String,
    val content: List<OutputText>
)

@JsonClass(generateAdapter = true)
data class OutputText(
    val text: String
)