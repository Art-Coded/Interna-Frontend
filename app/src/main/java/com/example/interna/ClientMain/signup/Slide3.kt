package com.example.interna.ClientMain.signup

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlideThree() {
    val scrollState = rememberScrollState()
    val isDarkTheme = isSystemInDarkTheme()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // State variables for form fields
    var sex by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var companyAddress by remember { mutableStateOf("") }
    var moaStatus by remember { mutableStateOf("") }
    var regionalLocation by remember { mutableStateOf("") }
    var internshipStartDate by remember { mutableStateOf<Long?>(null) }
    var internshipHours by remember { mutableStateOf("") }
    var managerName by remember { mutableStateOf("") }
    var managerContact by remember { mutableStateOf("") }

    // Work Schedule States
    var startDay by remember { mutableStateOf("") }
    var endDay by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    // State for date picker
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // State for dropdown menus
    var sexExpanded by remember { mutableStateOf(false) }
    var moaExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Monitoring",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp),
            )

            Text(
                text = "Please make sure to answer the sheet with complete and accurate information. This is important for monitoring your OJT progress and ensuring proper documentation.",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp))
            {
                // Sex Dropdown (M or F)
                ExposedDropdownMenuBox(
                    expanded = sexExpanded,
                    onExpandedChange = { sexExpanded = !sexExpanded }
                ) {
                    OutlinedTextField(
                        value = sex,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Sex *") },
                        placeholder = { Text("Select M or F") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sexExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = sexExpanded,
                        onDismissRequest = { sexExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Male (M)") },
                            onClick = {
                                sex = "M"
                                sexExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Female (F)") },
                            onClick = {
                                sex = "F"
                                sexExpanded = false
                            }
                        )
                    }
                }

                // Complete Name of Host/Company
                OutlinedTextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    label = { Text("Complete Name of Host/Company (HTE) *") },
                    placeholder = { Text("Enter company name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                // Complete Address of HTE
                OutlinedTextField(
                    value = companyAddress,
                    onValueChange = { companyAddress = it },
                    label = { Text("Complete Address of HTE *") },
                    placeholder = { Text("Enter complete address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                // MOA Status Dropdown
                ExposedDropdownMenuBox(
                    expanded = moaExpanded,
                    onExpandedChange = { moaExpanded = !moaExpanded }
                ) {
                    OutlinedTextField(
                        value = moaStatus,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("MOA Status *") },
                        placeholder = { Text("Select MOA status") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = moaExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = moaExpanded,
                        onDismissRequest = { moaExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Notarized and Submitted to the SIPP Coordinator") },
                            onClick = {
                                moaStatus = "Notarized and Submitted to the SIPP Coordinator"
                                moaExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Still Processing") },
                            onClick = {
                                moaStatus = "Still Processing"
                                moaExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("None") },
                            onClick = {
                                moaStatus = "None"
                                moaExpanded = false
                            }
                        )
                    }
                }

                // Regional Location of the HTE
                OutlinedTextField(
                    value = regionalLocation,
                    onValueChange = { regionalLocation = it },
                    label = { Text("Regional Location of the HTE *") },
                    placeholder = { Text("Enter regional location") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                // Start of Internship (Date Picker)
                val formattedDate = if (internshipStartDate != null) {
                    SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(internshipStartDate!!))
                } else ""

                OutlinedTextField(
                    value = formattedDate,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Start of Internship *") },
                    placeholder = { Text("Select start date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    trailingIcon = {
                        TextButton(onClick = { showDatePicker = true }) {
                            Text("Select")
                        }
                    }
                )

                // How many hours of internship to render
                OutlinedTextField(
                    value = internshipHours,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            internshipHours = newValue
                        }
                    },
                    label = { Text("Hours of Internship to Render *") },
                    placeholder = { Text("Enter number of hours") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                // Name of Manager/Immediate Supervisor
                OutlinedTextField(
                    value = managerName,
                    onValueChange = { managerName = it },
                    label = { Text("Name of Manager/Immediate Supervisor *") },
                    placeholder = { Text("Enter manager's name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                // Manager/Immediate Supervisor Contact Number
                OutlinedTextField(
                    value = managerContact,
                    onValueChange = { managerContact = it },
                    label = { Text("Manager Contact Number *") },
                    placeholder = { Text("Enter contact number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                // Submit Button
                Button(
                    onClick = {
                        // TODO: Implement form submission logic
                        // Validate and submit all the form data
                        val workScheduleSummary = if (startDay.isNotEmpty() && endDay.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty()) {
                            "$startDay to $endDay, $startTime to $endTime"
                        } else {
                            ""
                        }
                        // Use workScheduleSummary for submission
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(48.dp)
                ) {
                    Text("Submit Monitoring Information")
                }
                Spacer(modifier = Modifier.height(34.dp))
            }


        }

        // Date Picker Dialog
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            internshipStartDate = datePickerState.selectedDateMillis
                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDatePicker = false }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}