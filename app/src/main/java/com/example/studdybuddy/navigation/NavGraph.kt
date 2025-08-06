package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.repository.CourseRepository
import com.example.app.core.MainScreen
import com.example.app.feature.course.AddCourseScreen
import com.example.app.feature.course.CourseCalendarScreen
import com.example.app.feature.home.CoursesScreen
import com.example.app.feature.home.SignInScreen
import com.example.app.viewmodel.CourseViewModel
import com.example.app.viewmodel.CourseViewModelFactory
import com.example.studdybuddy.data.local.database.AppDatabase
import com.example.studdybuddy.navigation.Screen
import androidx.compose.runtime.collectAsState
import com.example.app.feature.auth.RegisterScreen
import com.example.app.feature.auth.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Auth flow
        composable(Screen.Auth.route) {
            SignInScreen(navController = navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        // Main app (bottom nav)
        composable(Screen.Home.route) {
            MainScreen(navController = navController)
        }

        composable(Screen.AddCourse.route) {
            val context = LocalContext.current
            val courseDao = AppDatabase.getInstance(context).courseDao()
            val taskDao = AppDatabase.getInstance(context).taskDao()
            val repository = CourseRepository(courseDao, taskDao)
            val factory = CourseViewModelFactory(repository)
            val viewModel: CourseViewModel = viewModel(factory = factory)

            AddCourseScreen(navController = navController, viewModel = viewModel)
        }

        composable("${Screen.CourseCalendar.route}/{courseId}") { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")?.toIntOrNull()

            val context = LocalContext.current
            val courseDao = AppDatabase.getInstance(context).courseDao()
            val taskDao = AppDatabase.getInstance(context).taskDao()
            val repository = CourseRepository(courseDao, taskDao)
            val viewModel: CourseViewModel = viewModel(factory = CourseViewModelFactory(repository))

            val allTasksState = viewModel.allTasks.collectAsState()
            val allTasks = allTasksState.value
//            println("These are the tasks in the db: $allTasks")
            if (courseId != null) {
                CourseCalendarScreen(courseId = courseId, allTasks = allTasks)
            }
        }


    }
}
