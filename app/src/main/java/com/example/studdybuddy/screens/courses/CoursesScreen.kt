package com.example.app.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoursesScreen() {
    Scaffold(
        containerColor = Color.Green
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            item {
                CourseItem("EG5307- Mobile Technologies")
            }
            item {
                CourseItem("Second Item in list")
            }
        }
    }
}

@Composable
fun CourseItem(label: String) {
    ElevatedCard(
        modifier = Modifier
            .height(82.dp)
//            .border(2.dp, Color.DarkGray)
            .fillMaxWidth()
        ,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(size = 16.dp), // Set all corners to 16.dp radius
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp // Adjust the elevation as needed
        ),
//        verticalArrangement = Arrangement.SpaceAround // Distributes items vertically
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxHeight()
//                .width(20.dp)
//                .background(color = Color.Red) // Sets a solid red background
//            ,
//        ) {}

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.Center // Distributes items vertically
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            LinearProgressIndicator(
                progress = {
                    0.5f
                },
                modifier = Modifier.fillMaxWidth(),
//                color = ProgressIndicatorDefaults.linearColor,
                color = Color(0xFF6750A4),
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}