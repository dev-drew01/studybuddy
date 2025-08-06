package com.example.studdybuddy.data.local.database

import com.example.app.data.local.dao.CourseDao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app.data.local.dao.TaskDao
import com.example.studdybuddy.data.local.entity.CourseEntity
import com.example.studdybuddy.data.local.entity.TaskEntity

@Database(entities = [CourseEntity::class, TaskEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "course_db"
                )
                    .fallbackToDestructiveMigration(true) // 💥 wipes and rebuilds DB on schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

