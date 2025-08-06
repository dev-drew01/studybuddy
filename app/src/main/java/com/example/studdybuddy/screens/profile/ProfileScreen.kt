package com.example.app.feature.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.example.studdybuddy.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser
Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(
                text="Profile",
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
) {
    padding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding).padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                user?.let {
                    Text("Email: ${it.email ?: "N/A"}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(10.dp))
                } ?: run {
                    Text("No user signed in.")
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(Screen.Auth.route) {
                popUpTo(0) // Clear backstack
            }
        }) {
            Text("Log Out")
        }
    }
}
}
