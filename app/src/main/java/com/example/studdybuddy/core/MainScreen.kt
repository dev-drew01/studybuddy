package com.example.app.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.feature.course.AddCourseScreen
import com.example.app.feature.home.CoursesScreen
import com.example.app.feature.home.HomeScreen
import com.example.app.navigation.BottomNavigationBar
import com.example.app.viewmodel.CourseViewModel
import com.example.studdybuddy.navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.repository.CourseRepository
import com.example.app.feature.course.CourseCalendarScreen
import com.example.app.feature.profile.ProfileScreen
import com.example.app.viewmodel.CourseViewModelFactory
import com.example.studdybuddy.data.local.database.AppDatabase

@Composable
fun MainScreen(navController: NavHostController) {
    // Inner navController for navigating between Home/Profile
    val context = LocalContext.current
    val courseDao = AppDatabase.getInstance(context).courseDao()
    val taskDao = AppDatabase.getInstance(context).taskDao()
    val repository = CourseRepository(courseDao, taskDao)
    val factory = CourseViewModelFactory(repository)
    val courseViewModel: CourseViewModel = viewModel(factory = factory)

    val bottomNavController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Courses.route) {
                CoursesScreen(navController = navController, viewModel= courseViewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController,)
            }
        }
    }
}
