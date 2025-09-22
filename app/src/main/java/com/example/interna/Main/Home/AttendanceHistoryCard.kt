package com.example.interna.Main.Home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.interna.ui.theme.blue_green
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AttendanceHistoryCard() {
    val isDarkTheme = isSystemInDarkTheme()
    val lineColor = MaterialTheme.colorScheme.primary

    val attendanceList = listOf(
        Triple("Monday", "09:01 AM - 06:03 PM", "Sept 15, 2025"),   // ✅
        Triple("Tuesday", "09:05 AM - 06:10 PM", "Sept 16, 2025"), // ✅
        Triple("Wednesday", "09:30 AM - 05:00 PM", "Sept 17, 2025"), // ⚠ Late
        Triple("Thursday", "---------------", "Sept 18, 2025"),     // ❌ Absent
        Triple("Friday", "09:15 AM - 05:00 PM", "Sept 19, 2025")   // ⚠ Late
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
    val isAbsent = time == "---------------"

    val totalHours: Double
    val isLate: Boolean

    if (!isAbsent) {
        val timeRange = time.split(" - ")
        val formatter = SimpleDateFormat("hh:mm a", Locale.US)
        val clockIn = formatter.parse(timeRange[0])
        val clockOut = formatter.parse(timeRange[1])
        val diffMinutes = ((clockOut.time - clockIn.time) / (1000 * 60)).toInt()
        totalHours = diffMinutes / 60.0
        isLate = totalHours < 8
    } else {
        totalHours = 0.0
        isLate = false
    }

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

        // Details (left side)
        Column(
            modifier = Modifier.weight(1f).padding(vertical = 12.dp)
        ) {
            Text(
                text = status,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 6.sp
            )
            if (!isAbsent) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    lineHeight = 6.sp
                )
            } else {
                Text(
                    text = "---------------",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = date,
                fontSize = 12.sp,
                lineHeight = 6.sp
            )
        }

        // Right side
        if (!isAbsent) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isLate) Icons.Default.Warning else Icons.Default.Check,
                        contentDescription = null,
                        tint = if (isLate) Color.Red else blue_green,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${"%.1f".format(totalHours)} hrs",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (isLate) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .background(Color.Red.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Late",
                            fontSize = 10.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        } else {
            // Show Absent on the right side too
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Absent",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }

        }
    }
}

