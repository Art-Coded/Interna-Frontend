package com.example.interna.Main.Home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AttendanceHistoryCard() {
    val isDarkTheme = isSystemInDarkTheme()
    val lineColor = MaterialTheme.colorScheme.primary

    val attendanceList = listOf(
        Triple("Monday", "09:01 AM - 06:03 PM", "Sept 15, 2025"),
        Triple("Tuesday", "09:05 AM - 06:10 PM", "Sept 16, 2025"),
        Triple("Wednesday", "09:02 AM - 06:11 PM", "Sept 17, 2025"),
        Triple("Thursday", "09:08 AM - 06:07 PM", "Sept 18, 2025"),
        Triple("Friday", "09:00 AM - 05:58 PM", "Sept 19, 2025")
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isDarkTheme) {
                        Color.Black.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Recent Attendance",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    attendanceList.forEachIndexed { index, (status, time, date) ->
                        TimelineRow(
                            status = status,
                            time = time,
                            date = date,
                            isFirst = index == 0,
                            isLast = index == attendanceList.size - 1,
                            lineColor = lineColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineRow(
    status: String,
    time: String,
    date: String,
    isFirst: Boolean,
    isLast: Boolean,
    lineColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Timeline (circle + lines)
        Canvas(
            modifier = Modifier
                .width(24.dp)
                .fillMaxHeight()
                .offset(y = (-15).dp)
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2

            if (!isFirst) {
                drawLine(
                    color = lineColor,
                    start = Offset(centerX, 0f),
                    end = Offset(centerX, centerY),
                    strokeWidth = 4f
                )
            }
            if (!isLast) {
                drawLine(
                    color = lineColor,
                    start = Offset(centerX, centerY),
                    end = Offset(centerX, size.height),
                    strokeWidth = 4f
                )
            }
            drawCircle(
                color = lineColor,
                radius = 12f,
                center = Offset(centerX, centerY)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Details
        Column(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(
                text = status,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 6.sp
            )
            Text(
                text = time,
                fontSize = 12.sp,
                lineHeight = 6.sp
            )
            Text(
                text = date,
                fontSize = 12.sp,
                lineHeight = 6.sp
            )
        }
    }
}
