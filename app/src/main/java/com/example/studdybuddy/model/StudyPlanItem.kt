package com.example.app.model  // adjust if needed
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudyPlanItem(
    val date: String,
    val tasks: List<StudyTask>
)
