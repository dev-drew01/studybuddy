package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.core.MainScreen
import com.example.app.feature.course.AddCourseScreen
import com.example.app.feature.home.CoursesScreen
import com.example.app.feature.home.SignInScreen
import com.example.studdybuddy.navigation.Screen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Auth flow
        composable(Screen.Auth.route) {
            SignInScreen(navController = navController)
        }

        // Main app (bottom nav)
        composable(Screen.Home.route) {
            MainScreen(navController = navController)
        }
    }
}
