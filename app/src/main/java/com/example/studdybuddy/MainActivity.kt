package com.example.studdybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.studdybuddy.ui.theme.StudyBuddyTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.app.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyBuddyTheme {
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    StudyBuddyTheme {
        Surface {
            val navController = rememberNavController()
            AppNavGraph(navController = navController)
        }
    }
}
