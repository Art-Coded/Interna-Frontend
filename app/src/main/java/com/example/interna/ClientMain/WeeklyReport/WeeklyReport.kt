package com.example.interna.ClientMain.WeeklyReport

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*
import com.example.interna.R
import com.example.interna.ui.theme.gradient_end
import com.example.interna.ui.theme.gradient_start
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyReportScreen(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()

    val scrollState = rememberScrollState()
    var reportText by remember { mutableStateOf("") }
    val currentWeek = SimpleDateFormat("'Week' w 'of' yyyy", Locale.getDefault()).format(Date())
    val dateRange = "September 16 - September 20, 2025"

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Weekly Reports",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Current Week Info Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                    colors = listOf(
                                        gradient_start,
                                        gradient_end
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Current Week",
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        text = currentWeek,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = dateRange,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Week Status
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                WeekStatusItem(
                                    label = "Days Completed",
                                    value = "4/5",
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                                WeekStatusItem(
                                    label = "Hours Logged",
                                    value = "32/40",
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                                WeekStatusItem(
                                    label = "Report Status",
                                    value = if (reportText.isNotEmpty()) "Draft" else "Pending",
                                    color = if (reportText.isNotEmpty()) Color.White else Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                // Report Writing Card
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
                                    text = "Write Your Weekly Report",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "${reportText.length}/2000",
                                    fontSize = 10.sp,
                                    color = if (reportText.length > 1800) Color(0xFFFF5722) else Color(0xFF666666)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Describe your weekly activities, learnings, challenges, and achievements during your OJT.",
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Text Input Area
                            OutlinedTextField(
                                value = reportText,
                                onValueChange = { if (it.length <= 2000) reportText = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp),
                                placeholder = {
                                    Text(
                                        text = "Start writing your weekly report here...\n\nSuggested topics to cover:\n• Main tasks and activities completed\n• New skills learned or improved\n• Challenges faced and how you overcame them\n• Achievements and accomplishments\n• Areas for improvement\n• Plans for next week",
                                        fontSize = 12.sp,
                                        color = Color(0xFFBBBBBB)
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = gradient_start
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Action Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { /* Save as draft */ },
                                    modifier = Modifier.weight(1f),
                                    enabled = reportText.isNotEmpty()
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_tutorial),
                                        contentDescription = "Save Draft",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Save Draft", fontSize = 12.sp)
                                }

                                Button(
                                    onClick = { /* Submit report */ },
                                    modifier = Modifier.weight(1f),
                                    enabled = reportText.length >= 100,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF4CAF50)
                                    )
                                ) {
                                    Icon(
                                        Icons.Default.Send,
                                        contentDescription = "Submit",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Submit", fontSize = 12.sp)
                                }
                            }

                            if (reportText.length < 100) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Minimum 100 characters required to submit (${100 - reportText.length} more needed)",
                                    fontSize = 10.sp,
                                    color = Color(0xFFFF9800)
                                )
                            }

                            //weekly report guidelines part
                            Box(modifier = Modifier.padding(16.dp).background(Color(0xFF2196F3).copy(alpha = 0.1f)))
                            Column{
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Guidelines",
                                        tint = gradient_start,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Report Writing Guidelines",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    GuidelineItem(
                                        text = "Write in detail about your daily activities and tasks"
                                    )
                                    GuidelineItem(
                                        text = "Include specific examples of what you learned"
                                    )
                                    GuidelineItem(
                                        text = "Mention any challenges and how you addressed them"
                                    )
                                    GuidelineItem(
                                        text = "Be honest and reflective about your experience"
                                    )
                                    GuidelineItem(
                                        text = "(Optional) Use professional language and proper grammar"
                                    )
                                }
                            }
                        }
                    }
                }

                // Previous Reports Card
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
                            text = "• Submit your report by Friday 5:00 PM\n• Save drafts regularly to avoid losing your work\n• Review your report before final submission\n• Contact your supervisor if you need help",
                            fontSize = 12.sp,
                            color = Color(0xFFFF9800).copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun WeekStatusItem(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 8.sp,
            color = color
        )
    }
}

@Composable
fun GuidelineItem(
    text: String
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp
        )
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
                fontSize = 16.sp,
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