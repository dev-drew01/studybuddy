package com.example.app.feature.course

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.app.model.StudyPlanItem
import com.example.app.utils.OpenAiApiClient
import com.example.app.viewmodel.CourseViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.app.utils.OpenAiRequest
import com.example.app.utils.Message
import com.example.studdybuddy.data.local.entity.TaskEntity
import com.example.studdybuddy.data.local.database.AppDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch
import com.squareup.moshi.Json
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    navController: NavHostController,
    viewModel: CourseViewModel
) {
    val context = LocalContext.current
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    var courseName by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    fun openDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, day ->
                calendar.set(year, month, day)
                onDateSelected(dateFormatter.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Course", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = courseName,
                onValueChange = { courseName = it },
                label = { Text("Course Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { openDatePicker { startDate = it } },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(8.dp), // Creates rounded corners with an 8dp radius
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
                border = BorderStroke(width = 1.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(if (startDate.isBlank()) "Select Start Date" else "Start Date: $startDate")
                }
            }

            Button(
                onClick = { openDatePicker { endDate = it } },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(8.dp), // Creates rounded corners with an 8dp radius
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
                border = BorderStroke(width = 1.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(if (endDate.isBlank()) "Select End Date" else "End Date: $endDate")
                }
            }

            Button(
                onClick = {
                    if (courseName.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()) {
                        isLoading = true
                        // 1. Save to Room
                        viewModel.addCourse(courseName, startDate, endDate)

                        // 2. Trigger OpenAI API
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val prompt = """
                        Generate a daily study plan for the course $courseName from $startDate to $endDate. Respond only in JSON. 
                        The response should be an array of objects, where each object contains a 'date' 
                        in YYYY-MM-DD format and a 'tasks' array with 1–3 learning tasks. 
                        Each task should include a 'topic', 'start_time', and 'end_time'. 
                        Do not group by week. No explanation outside the JSON.
                    """.trimIndent()
                                val response = OpenAiApiClient.apiService.getStudyPlan(
                                    request = OpenAiRequest(
                                        model = "gpt-4",
                                        input = listOf(
                                            Message("system", "You are a helpful academic tutor. Always respond only with valid JSON."),
                                            Message("user", prompt)
                                        )
                                    )
                                )
                                val studyPlanJson = response.body()?.output?.firstOrNull()?.content?.firstOrNull()?.text

                                println("✅ Study plan generated: $studyPlanJson")

                                val moshi = Moshi.Builder().build()
                                val type = Types.newParameterizedType(List::class.java, StudyPlanItem::class.java)
                                val adapter: JsonAdapter<List<StudyPlanItem>> = moshi.adapter(type)

                                val plan = adapter.fromJson(studyPlanJson ?: "")

                                val taskEntities = plan?.flatMap { item ->
                                    item.tasks.map { task ->
                                        TaskEntity(
                                            date = item.date,
                                            startTime = task.startTime,
                                            endTime = task.endTime,
                                            topic = task.topic,
                                            question = "" // placeholder
                                        )
                                    }
                                } ?: emptyList()

                                viewModel.insertTasks(taskEntities)
                                withContext(Dispatchers.Main) {
                                    isLoading = false
                                    Toast.makeText(context, "Course added successfully!", Toast.LENGTH_SHORT).show()
                                    navController.navigateUp()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                withContext(Dispatchers.Main) {
                                    isLoading = false
                                    Toast.makeText(context, "Failed to add course.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Add")
                }
            }
        }
    }
}
