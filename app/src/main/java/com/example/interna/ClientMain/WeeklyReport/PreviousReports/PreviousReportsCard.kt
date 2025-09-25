package com.example.interna.ClientMain.WeeklyReport.PreviousReports

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R
import kotlinx.coroutines.launch

@Composable
fun PreviousReportsCard() {
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Previous Submitted Reports",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    val pressed = remember { mutableStateOf(false) }

                    Text(
                        text = "View All",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .graphicsLayer {
                                alpha = if (pressed.value) 0.5f else 1f
                            }
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        pressed.value = true
                                        try {
                                            awaitRelease()
                                        } finally {
                                            pressed.value = false
                                        }
                                    },
                                    onTap = {
                                        // handle click here
                                    }
                                )
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    PreviousReportItem(
                        week = "Week 5",
                        submittedOn = "Submitted on: Sep 13, 2025",
                        statusColor = Color(0xFF4CAF50)
                    )

                    PreviousReportItem(
                        week = "Week 4",
                        submittedOn = "Submitted on: Sep 6, 2025",
                        statusColor = Color(0xFF4CAF50)
                    )

                    PreviousReportItem(
                        week = "Week 3",
                        submittedOn = "Submitted on: Aug 30, 2025",
                        statusColor = Color(0xFF4CAF50)
                    )
                }
            }
        }
    }

    // Submission Tips Card
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF9800).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row{
                Icon(
                    painter = painterResource(R.drawable.ic_tutorial),
                    contentDescription = "Tips",
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Submission Tips",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFF9800)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "• Submit your report on schedule for your OJT supervisor to properly monitor you and your safety\n• Save drafts regularly to avoid losing your work\n• Review your report before final submission\n• Contact your supervisor if you need help",
                fontSize = 12.sp,
                color = Color(0xFFFF9800).copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun PreviousReportItem(
    week: String,
    submittedOn: String,
    statusColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                statusColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = week,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = submittedOn,
                fontSize = 12.sp,
                color = Color(0xFF666666)
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            val scope = rememberCoroutineScope()
            IconButton(
                onClick = {
                    scope.launch {

                    }
                },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_view),
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.height(24.dp) // size of the icon inside
                )
            }
        }
    }
}