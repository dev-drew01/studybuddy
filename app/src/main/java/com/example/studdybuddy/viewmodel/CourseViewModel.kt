package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.repository.CourseRepository
import com.example.studdybuddy.data.local.entity.CourseEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow // only if needed

class CourseViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    fun addCourse(name: String, start: String, end: String) {
        val course = CourseEntity(name = name, startDate = start, endDate = end)
        viewModelScope.launch {
            repository.insertCourse(course)
        }
    }

    val allCourses: StateFlow<List<CourseEntity>> =
        repository.allCourses.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList<CourseEntity>()
        )
}
