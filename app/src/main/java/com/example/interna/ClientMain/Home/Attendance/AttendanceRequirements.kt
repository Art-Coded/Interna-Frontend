package com.example.interna.ClientMain.Home.Attendance

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceRequirements() {

    // Work Schedule States
    var startDay by remember { mutableStateOf("") }
    var endDay by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    var startDayExpanded by remember { mutableStateOf(false) }
    var endDayExpanded by remember { mutableStateOf(false) }
    var startTimeExpanded by remember { mutableStateOf(false) }
    var endTimeExpanded by remember { mutableStateOf(false) }

    // Days of the week (excluding Sunday)
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    // Common work hours
    val timeSlots = listOf(
        "6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM",
        "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM",
        "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM",
        "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM",
        "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"
    )

    Text(
        text = "Work Schedule *",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )

    Text(
        text = "You can edit this later if you think you made a mistake.",
        fontSize = 14.sp,
        lineHeight = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    )


    // Row 1: Work Days
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Start Day Dropdown
        ExposedDropdownMenuBox(
            expanded = startDayExpanded,
            onExpandedChange = { startDayExpanded = !startDayExpanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = startDay,
                onValueChange = {},
                readOnly = true,
                label = { Text("From") },
                placeholder = { Text("Start day") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = startDayExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(end = 4.dp)
            )
            ExposedDropdownMenu(
                expanded = startDayExpanded,
                onDismissRequest = { startDayExpanded = false }
            ) {
                daysOfWeek.forEach { day ->
                    DropdownMenuItem(
                        text = { Text(day) },
                        onClick = {
                            startDay = day
                            startDayExpanded = false
                        }
                    )
                }
            }
        }

        Text(
            text = "to",
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // End Day Dropdown
        ExposedDropdownMenuBox(
            expanded = endDayExpanded,
            onExpandedChange = { endDayExpanded = !endDayExpanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = endDay,
                onValueChange = {},
                readOnly = true,
                label = { Text("To") },
                placeholder = { Text("End day") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = endDayExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            ExposedDropdownMenu(
                expanded = endDayExpanded,
                onDismissRequest = { endDayExpanded = false }
            ) {
                daysOfWeek.forEach { day ->
                    DropdownMenuItem(
                        text = { Text(day) },
                        onClick = {
                            endDay = day
                            endDayExpanded = false
                        }
                    )
                }
            }
        }
    }

    // Row 2: Work Hours
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Start Time Dropdown
        ExposedDropdownMenuBox(
            expanded = startTimeExpanded,
            onExpandedChange = { startTimeExpanded = !startTimeExpanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = startTime,
                onValueChange = {},
                readOnly = true,
                label = { Text("Start Time") },
                placeholder = { Text("From") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = startTimeExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(end = 4.dp)
            )
            ExposedDropdownMenu(
                expanded = startTimeExpanded,
                onDismissRequest = { startTimeExpanded = false }
            ) {
                timeSlots.forEach { time ->
                    DropdownMenuItem(
                        text = { Text(time) },
                        onClick = {
                            startTime = time
                            startTimeExpanded = false
                        }
                    )
                }
            }
        }

        Text(
            text = "to",
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // End Time Dropdown
        ExposedDropdownMenuBox(
            expanded = endTimeExpanded,
            onExpandedChange = { endTimeExpanded = !endTimeExpanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = endTime,
                onValueChange = {},
                readOnly = true,
                label = { Text("End Time") },
                placeholder = { Text("To") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = endTimeExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            ExposedDropdownMenu(
                expanded = endTimeExpanded,
                onDismissRequest = { endTimeExpanded = false }
            ) {
                timeSlots.forEach { time ->
                    DropdownMenuItem(
                        text = { Text(time) },
                        onClick = {
                            endTime = time
                            endTimeExpanded = false
                        }
                    )
                }
            }
        }
    }

    // Display selected work schedule
    if (startDay.isNotEmpty() && endDay.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty()) {
        Text(
            text = "Selected: $startDay to $endDay, $startTime to $endTime",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}
