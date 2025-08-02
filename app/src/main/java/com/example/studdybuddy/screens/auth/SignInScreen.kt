package com.example.app.feature.home
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.app.ui.components.AuthInput

@Composable
fun SignInScreen() {
    var text by remember { mutableStateOf("") }
    Scaffold () {
        padding ->
        Column(
            modifier = Modifier
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to Study Buddy")
            Text("Sign in to continue")
            AuthInput(
                value = text,
                onValueChange = { newValue: String -> // Explicitly declare 'newValue' as String
                    text = newValue
                },
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon"
                    )
                },
            )
            AuthInput(
                value = text,
                onValueChange = { newValue: String -> // Explicitly declare 'newValue' as String
                    text = newValue
                },
                label = "Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon"
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text("Submit")
                }
        }
    }
}
