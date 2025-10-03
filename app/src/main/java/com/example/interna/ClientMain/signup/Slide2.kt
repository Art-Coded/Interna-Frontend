package com.example.interna.ClientMain.signup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.example.interna.ClientMain.Requirements.Requirement
import com.example.interna.ClientMain.Requirements.RequirementCard
import com.example.interna.ClientMain.Requirements.RequirementStatus
import com.example.interna.ui.theme.gradient_end
import com.example.interna.ui.theme.gradient_start

@Composable
fun SlideTwo() {
    val scrollState = rememberScrollState()
    val isDarkTheme = isSystemInDarkTheme()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                .verticalScroll(scrollState) //most likely to change into lazycolumn in actual setup?
        ) {

            Text(
                text = "Requirements",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp),
            )

            Text(
                text = "If you're having trouble with document submission or approval status, contact your OJT Advisor/coordinator.",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                            .padding(16.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Completion Progress",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Text(
                                    text = "3 of 6 completed",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            LinearProgressIndicator(
                                progress = { 0.5f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp),
                                color = Color(0xFF4CAF50),
                                trackColor = Color(0xFF4CAF50).copy(alpha = 0.2f),
                                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "3 requirements submitted",
                                    fontSize = 10.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "3 requirements pending",
                                    fontSize = 10.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                // Completed Requirements
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    RequirementCard(
                        requirement = Requirement(
                            title = "Resume/CV",
                            description = "Personal resume and curriculum vitae",
                            status = RequirementStatus.APPROVED
                        ),
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        requirement = Requirement(
                            title = "Endorsement Letter",
                            description = "Official letter from academic department",
                            status = RequirementStatus.APPROVED
                        ),
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        requirement = Requirement(
                            title = "Medical Certificate",
                            description = "Health clearance for on-site training",
                            status = RequirementStatus.APPROVED
                        ),
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        requirement = Requirement(
                            title = "Daily Time Record",
                            description = "Weekly timesheet documentation",
                            status = RequirementStatus.PENDING_UPLOAD
                        ),
                        borderColor = Color.LightGray
                    )

                    RequirementCard(
                        requirement = Requirement(
                            title = "Training Agreement",
                            description = "Signed agreement between school and company",
                            status = RequirementStatus.PENDING_UPLOAD
                        ),
                        borderColor = Color.LightGray
                    )

                    RequirementCard(
                        requirement = Requirement(
                            title = "Company MOA",
                            description = "Memorandum of Agreement document.",
                            status = RequirementStatus.WARNING,
                            warningReason = "This is not the correct document upload."
                        ),
                        borderColor = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // TODO: Navigate to Next
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = gradient_start,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 12.dp)
                ) {
                    Text(text = "Next")
                }

                Spacer(modifier = Modifier.height(34.dp))
            }
        }
    }
}