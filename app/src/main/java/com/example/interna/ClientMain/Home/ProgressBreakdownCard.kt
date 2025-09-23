package com.example.interna.ClientMain.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R

@Composable
fun ProgressBreakdownCard() {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_progress),
                        contentDescription = "Progress",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Progress Breakdown",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProgressBreakdownItem(
                        painter = painterResource(R.drawable.ic_clock),
                        label = "Training Hours",
                        progress = "312 / 500 hours",
                        percentage = 62, // (312 ÷ 500) * 100
                        color = Color(0xFF2196F3),
                        remainingText = "188 hours remaining"
                    )

                    ProgressBreakdownItem(
                        painter = painterResource(R.drawable.ic_calendar),
                        label = "Total Internship Days",
                        progress = "39 / 62.5 days",
                        percentage = 62, // (39 ÷ 62.5) * 100
                        color = Color(0xFF4CAF50),
                        remainingText = "23.5 days remaining"
                    )

                    ProgressBreakdownItem(
                        painter = painterResource(R.drawable.ic_filemove),
                        label = "Requirements",
                        progress = "2 / 6 completed",
                        percentage = 33, // (2 ÷ 6) * 100
                        color = Color(0xFF9C27B0),
                        remainingText = "4 requirements pending"
                    )

                    ProgressBreakdownItem(
                        painter = painterResource(R.drawable.ic_book),
                        label = "Weekly Journals",
                        progress = "12 / 20 entries",
                        percentage = 60, // (12 ÷ 20) * 100
                        color = Color(0xFFFF9800),
                        remainingText = "8 journals pending"
                    )

                }
            }
        }
    }
}

@Composable
fun ProgressBreakdownItem(
    painter: Painter,
    label: String,
    progress: String,
    percentage: Int,
    color: Color,
    remainingText: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painter,
                    contentDescription = label,
                    tint = color,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = progress,
                fontSize = 12.sp,
            )
        }

        LinearProgressIndicator(
            progress = { percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color,
            trackColor = color.copy(alpha = 0.2f),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$percentage% Complete",
                fontSize = 10.sp,
            )
            Text(
                text = remainingText,
                fontSize = 10.sp,
            )
        }
    }
}
