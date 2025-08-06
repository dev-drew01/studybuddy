package com.example.app.model  // adjust if needed
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudyTask(
    val topic: String,
    @Json(name = "start_time") val startTime: String,
    @Json(name = "end_time") val endTime: String
)
