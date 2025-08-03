// TaskEntity.kt
package com.example.studdybuddy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val startTime: String,
    val endTime: String,
    val topic: String,
    val question: String // Tied to the topic for learning/review
)