package com.example.app.feature.home
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app.ui.components.AuthInput
import fontFamily

@Composable
fun SignInScreen(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    Scaffold (
        modifier = Modifier.padding(16.dp), // scaffold itself padded
    ) {
        padding ->
        Column(
            modifier = Modifier
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to Study Buddy",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0XFF223263),
                modifier = Modifier.padding(top = 32.dp)
            )
            Text("Sign in to continue",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0XFF9098B1),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            AuthInput(
                value = text,
                onValueChange = { newValue: String -> // Explicitly declare 'newValue' as String
                    text = newValue
                },
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
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
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Email Icon"
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(57.dp)
                        .padding(top = 10.dp)
                    ,
                    onClick = {},
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF003F5F),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text="Sign in",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily
                    )
                }
        }
    }
}
