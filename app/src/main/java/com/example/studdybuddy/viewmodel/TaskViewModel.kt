package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.repository.TaskRepository
import com.example.studdybuddy.data.local.entity.TaskEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    fun getTasksByDate(date: String): StateFlow<List<TaskEntity>> =
        repository.getTasksByDate(date)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
}
