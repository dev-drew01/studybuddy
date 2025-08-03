package com.app.repository

import kotlinx.coroutines.flow.Flow
import com.example.app.data.local.dao.CourseDao
import com.example.studdybuddy.data.local.entity.CourseEntity

class CourseRepository(private val courseDao: CourseDao) {

    // 🔄 Reactive course list (Flow)
    val allCourses: Flow<List<CourseEntity>> = courseDao.getAllCourses()

    // ➕ Insert a course
    suspend fun insertCourse(course: CourseEntity) {
        courseDao.insertCourse(course)
    }

    // ❌ Delete a course
    suspend fun deleteCourse(course: CourseEntity) {
        courseDao.deleteCourse(course)
    }

    // 🔍 Optional: one-time fetch
    suspend fun getCoursesOnce(): List<CourseEntity> {
        return courseDao.getAllCoursesOnce()
    }
}
