package com.example.studdybuddy.navigation

sealed class Screen(val route: String) {
    // Authentication screen
    object Auth : Screen("auth")

    // Main app screens (inside bottom navigation)
    object Home : Screen("home")
    object Courses : Screen("courses")
    object Profile : Screen("profile")
    object AddCourse : Screen("add_course")
}
