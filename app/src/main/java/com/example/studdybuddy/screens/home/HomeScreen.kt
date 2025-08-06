package com.example.app.feature.home

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.ZeroLineProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text="Dashboard",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color(0XFF003F5F)
                ) },
                navigationIcon = {
                },
                actions = {
                }
            )
        }
    ) {
        padding ->
        Column(
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp),
        ) {
            ColumnChart(
                modifier = Modifier.height(350.dp),
                data = remember {
                    listOf(
                        Bars(
                            label = "Mon",
                            values = listOf(
                                Bars.Data(value = 14.0, color = SolidColor(Color.Red))
                            )
                        ),
                        Bars(
                            label = "Tue",
                            values = listOf(
                                Bars.Data(value = 10.0, color = SolidColor(Color.Blue))
                            )
                        ),
                        Bars(
                            label = "Wed",
                            values = listOf(
                                Bars.Data(value = 3.0, color = SolidColor(Color.Red))
                            )
                        ),
                        Bars(
                            label = "Thu",
                            values = listOf(
                                Bars.Data(value = 9.0, color = SolidColor(Color.Yellow))
                            )
                        ),
                        Bars(
                            label = "Fri",
                            values = listOf(
                                Bars.Data(value = 7.0, color = SolidColor(Color.Green))
                            )
                        ),
                        Bars(
                            label = "Sat",
                            values = listOf(
                                Bars.Data(value = 15.0, color = SolidColor(Color.Red))
                            )
                        ),
                        Bars(
                            label = "Sun",
                            values = listOf(
                                Bars.Data(value = 2.0, color = SolidColor(Color.Blue))
                            )
                        ),
                    )
                },
                barProperties = BarProperties(
                    spacing = 1.dp,
//                    strokeWidth = 10.dp,
                ),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text="Your daily progress",
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp,
                color = Color(0XFF003F5F)
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
