package com.app.repository

import kotlinx.coroutines.flow.Flow
import com.example.app.data.local.dao.CourseDao
import com.example.app.data.local.dao.TaskDao
import com.example.studdybuddy.data.local.entity.CourseEntity
import com.example.studdybuddy.data.local.entity.TaskEntity

class CourseRepository(
    private val courseDao: CourseDao,
    private val taskDao: TaskDao
) {

    // 🔄 Reactive course list (Flow)
    val allCourses: Flow<List<CourseEntity>> = courseDao.getAllCourses()

    // ➕ Insert a course
    suspend fun insertCourse(course: CourseEntity) {
        courseDao.insertCourse(course)
    }

    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun insertTasks(tasks: List<TaskEntity>) {
        taskDao.insertTasks(tasks)
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
