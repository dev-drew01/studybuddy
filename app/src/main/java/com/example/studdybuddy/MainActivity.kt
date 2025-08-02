package com.example.studdybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studdybuddy.ui.theme.StuddyBuddyTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.app.feature.home.CoursesScreen
import com.example.app.feature.home.HomeScreen
import com.example.app.feature.home.SignInScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StuddyBuddyTheme {
                BottomNavApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StuddyBuddyTheme {
        Greeting("Android")
    }
}

@Composable
fun BottomNavApp() {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Courses", Icons.Default.Search),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) {
        SignInScreen()
//        when (selectedItem) {
//            0 -> HomeScreen()
//            1 -> CoursesScreen()
//            2 -> ProfileScreen()
//        }
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector)

@Composable
fun ProfileScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Profile Screen", style = MaterialTheme.typography.h4)
    }
}