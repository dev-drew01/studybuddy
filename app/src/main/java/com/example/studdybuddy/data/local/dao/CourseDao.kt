package com.example.app.data.local.dao

import androidx.room.*
import com.example.studdybuddy.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Delete
    suspend fun deleteCourse(course: CourseEntity)

    // ✅ Reactive stream of all courses
    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<CourseEntity>>

    // ✅ One-time fetch (optional)
    @Query("SELECT * FROM courses")
    suspend fun getAllCoursesOnce(): List<CourseEntity>
}
