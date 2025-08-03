package com.example.app.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.feature.course.AddCourseScreen
import com.example.app.feature.home.CoursesScreen
import com.example.app.feature.home.HomeScreen
import com.example.app.navigation.BottomNavigationBar
import com.example.studdybuddy.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    // Inner navController for navigating between Home/Profile
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
                CoursesScreen(navController = bottomNavController)
            }
            composable(Screen.AddCourse.route) {
                AddCourseScreen(navController = bottomNavController)
            }
        }
    }
}
