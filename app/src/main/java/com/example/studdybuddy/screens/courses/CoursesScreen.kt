package com.example.app.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app.viewmodel.CourseViewModel
import com.example.studdybuddy.navigation.Screen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.studdybuddy.data.local.entity.CourseEntity
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(
    navController: NavHostController,
    viewModel: CourseViewModel
) {
    val courses by viewModel.allCourses.collectAsState()
    Scaffold(
        modifier = Modifier.padding( 16.dp),
        topBar = {
            TopAppBar(
                title = { Text(
                    text="Courses",
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                 navController.navigate(Screen.AddCourse.route)
            }) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) {padding ->
        if (courses.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No courses added yet.")
            }
        } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(courses) { course ->
                CourseItem(course=course)
            }
        }
            }
    }
}

@Composable
fun CourseItem(course: CourseEntity) {
    ElevatedCard(
        modifier = Modifier
            .height(82.dp)
//            .border(2.dp, Color.DarkGray)
            .fillMaxWidth()
        ,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(size = 16.dp), // Set all corners to 16.dp radius
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp // Adjust the elevation as needed
        ),
//        verticalArrangement = Arrangement.SpaceAround // Distributes items vertically
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxHeight()
//                .width(20.dp)
//                .background(color = Color.Red) // Sets a solid red background
//            ,
//        ) {}

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.Center // Distributes items vertically
        ) {
            Text(
                text = course.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            LinearProgressIndicator(
                progress = {
                    0.5f
                },
                modifier = Modifier.fillMaxWidth(),
//                color = ProgressIndicatorDefaults.linearColor,
                color = Color(0xFF6750A4),
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}