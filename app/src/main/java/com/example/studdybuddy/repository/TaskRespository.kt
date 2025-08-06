package com.app.repository

import kotlinx.coroutines.flow.Flow
import com.example.app.data.local.dao.TaskDao
import com.example.studdybuddy.data.local.entity.TaskEntity

class TaskRepository(private val taskDao: TaskDao) {
    fun getTasksByDate(date: String): Flow<List<TaskEntity>> =
        taskDao.getTasksByDate(date)

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }
}
