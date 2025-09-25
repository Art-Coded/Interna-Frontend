package com.example.interna.ClientMain.WeeklyReport.ReportWriting

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R
import com.example.interna.ui.theme.gradient_start
import kotlin.text.isNotEmpty

enum class WeekStatus {
    SUBMITTED, DRAFT, PENDING, CURRENT
}

data class WeekData(
    val id: Int,
    val week: String,
    val dateRange: String,
    val status: WeekStatus,
    val daysCompleted: Int,
    val totalDays: Int,
    val hoursLogged: Int,
    val totalHours: Int,
    val draftText: String = ""
)
@Composable
fun ReportWritingCard() {
    val isDarkTheme = isSystemInDarkTheme()
    var reportText by remember { mutableStateOf("") }
    var showWeekSelector by remember { mutableStateOf(false) }
    var reportTexts by remember { mutableStateOf(mapOf<Int, String>()) }

    var selectedWeekId by remember { mutableStateOf(6) }

    val weekData = listOf(
        WeekData(2, "Week 2", "August 19 - August 23, 2025", WeekStatus.PENDING, 5, 5, 40, 40),
        WeekData(5, "Week 5", "September 9 - September 13, 2025", WeekStatus.PENDING, 5, 5, 40, 40),
        WeekData(6, "Week 6", "September 16 - September 20, 2025", WeekStatus.PENDING, 4, 5, 32, 40),
        WeekData(7, "Week 7", "September 23 - September 27, 2025", WeekStatus.PENDING, 3, 5, 24, 40)
    )

    val selectedWeek = weekData.find { it.id == selectedWeekId } ?: weekData[2]
    val pendingWeeks = weekData.filter { it.status == WeekStatus.PENDING || it.status == WeekStatus.CURRENT }

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

                // Week Selector
                OutlinedButton(
                    onClick = { showWeekSelector = !showWeekSelector },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF333333)
                    )
                ) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Select Week",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${selectedWeek.week} - ${selectedWeek.dateRange}",
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Image(
                        painter = painterResource(
                            id = if (showWeekSelector) R.drawable.ic_arrow_up
                            else R.drawable.ic_arrow_down
                        ),
                        contentDescription = if (showWeekSelector) "Collapse" else "Expand",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                }

                if (showWeekSelector) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isDarkTheme) {
                                        Color.Black.copy(alpha = 0.6f)
                                    } else {
                                        MaterialTheme.colorScheme.surface
                                    }
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            ) {
                                Text(
                                    text = "Select week to write report:",
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )

                                pendingWeeks.forEach { week ->
                                    Surface(
                                        onClick = {
                                            selectedWeekId = week.id
                                            showWeekSelector = false
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        color = if (selectedWeekId == week.id) Color(0xFF4CAF50).copy(alpha = 0.1f) else Color.Transparent
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column {
                                                Text(
                                                    text = week.week,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    color = if (selectedWeekId == week.id) gradient_start else MaterialTheme.colorScheme.onSurface
                                                )
                                                Text(
                                                    text = week.dateRange,
                                                    fontSize = 10.sp
                                                )
                                            }

                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {

                                                if (reportTexts[week.id]?.isNotEmpty() == true) {
                                                    AssistChip(
                                                        onClick = { },
                                                        label = { Text("Draft", fontSize = 8.sp) },
                                                        colors = AssistChipDefaults.assistChipColors(
                                                            containerColor = Color(0xFFFF9800).copy(alpha = 0.2f)
                                                        )
                                                    )
                                                } else if (week.status == WeekStatus.PENDING) {
                                                    AssistChip(
                                                        onClick = { },
                                                        label = { Text("Missing", fontSize = 8.sp) },
                                                        colors = AssistChipDefaults.assistChipColors(
                                                            containerColor = Color(0xFFFF5722).copy(alpha = 0.2f)
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
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
                            text = "Start writing your ${selectedWeek.week} report here...\n\nSuggested topics to cover:\n• Main tasks and activities completed\n• New skills learned or improved\n• Challenges faced and how you overcame them\n• Achievements and accomplishments\n• Areas for improvement\n• Plans for next week",
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
                            painter = painterResource(R.drawable.ic_save),
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
                Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom =16.dp).background(Color(0xFF2196F3).copy(alpha = 0.1f)))
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

                    Column{
                        Text(
                            text = "• Write in detail about your daily activities and tasks\n• Include specific examples of what you learned\n• Mention any challenges and how you addressed them\n• Be honest and reflective about youre experience\n• (Optional) Use professional language and proper grammar"
                        )
                    }
                }
            }
        }
    }
}