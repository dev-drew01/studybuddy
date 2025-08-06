package com.example.app.feature.course

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studdybuddy.data.local.entity.TaskEntity
import com.kizitonwose.calendar.compose.HorizontalCalendar
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun CourseCalendarScreen(
    courseId: Int,
    allTasks: List<TaskEntity>
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    val groupedTasks = remember(allTasks) {
        allTasks.groupBy { LocalDate.parse(it.date) }
    }
Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(
                text="Study Plan",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color(0XFF003F5F)
            ) },
            navigationIcon = {
            },
            actions = {
            }
        )
    },
) {padding ->
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Text(
//            text = "Study Plan",
//            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )

        HorizontalCalendar(
            dayContent = { day ->
                val isSelected = day.date == selectedDate
                DayContent(day.date, isSelected) {
                    selectedDate = day.date
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        val selectedTasks = groupedTasks[selectedDate] ?: emptyList()

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(selectedTasks) { task ->
                DummyTaskItem(task)
            }
        }
    }
}
}

@Composable
fun DayContent(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DummyTaskItem(task: TaskEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = task.topic, fontWeight = FontWeight.SemiBold)
            Text(text = "${task.startTime} - ${task.endTime}", style = MaterialTheme.typography.bodySmall)
        }
    }
}