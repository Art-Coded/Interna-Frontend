package com.example.interna.ClientMain.Requirements

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.interna.R
import com.example.interna.ui.theme.gradient_end
import com.example.interna.ui.theme.gradient_start

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequirementsScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 12.dp)
            ) {
                Text(
                    text = "Requirements",
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
                // Progress Overview Card
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
                        title = "Resume/CV",
                        description = "Personal resume and curriculum vitae",
                        status = RequirementStatus.APPROVED,
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        title = "Endorsement Letter",
                        description = "Official letter from academic department",
                        status = RequirementStatus.APPROVED,
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        title = "Medical Certificate",
                        description = "Health clearance for on-site training",
                        status = RequirementStatus.APPROVED,
                        borderColor = Color(0xFF4CAF50)
                    )

                    RequirementCard(
                        title = "Daily Time Record",
                        description = "Weekly timesheet documentation",
                        status = RequirementStatus.PENDING_UPLOAD,
                        borderColor = Color(0xFFF44336)
                    )

                    RequirementCard(
                        title = "Training Agreement",
                        description = "Signed agreement between school and company",
                        status = RequirementStatus.PENDING_UPLOAD,
                        borderColor = Color(0xFFF44336)
                    )

                    RequirementCard(
                        title = "Final Portfolio",
                        description = "Complete portfolio of OJT experience and learnings",
                        status = RequirementStatus.PENDING_UPLOAD,
                        borderColor = Color(0xFFF44336)
                    )
                }

                // Quick Actions Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Quick Actions",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { /* Upload document */ },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(80.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_filter),
                                        contentDescription = "Upload Document",
                                        tint = Color(0xFF2196F3),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        "Upload Document",
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            OutlinedButton(
                                onClick = { /* Download all */ },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(80.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_filter),
                                        contentDescription = "Download All",
                                        tint = Color(0xFF4CAF50),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        "Download All",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // Help Section
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Need Help?",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF2196F3)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "If you're having trouble with document submission or approval status, contact your OJT coordinator.",
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /* Contact support */ },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFF2196F3)
                            )
                        ) {
                            Text(
                                "Contact Support",
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

enum class RequirementStatus {
    APPROVED,
    PENDING_UPLOAD,
    OVERDUE,
    NOT_STARTED
}

@Composable
fun RequirementCard(
    title: String,
    description: String,
    status: RequirementStatus,
    borderColor: Color
) {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        start = 4.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Status indicator bar
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(60.dp)
                        .background(
                            borderColor,
                            shape = RoundedCornerShape(2.dp)
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            when(status) {
                                RequirementStatus.APPROVED -> Icons.Default.CheckCircle
                                RequirementStatus.PENDING_UPLOAD -> Icons.Default.Warning
                                RequirementStatus.OVERDUE -> Icons.Default.Warning
                                RequirementStatus.NOT_STARTED -> Icons.Default.AddCircle
                            },
                            contentDescription = status.name,
                            tint = borderColor,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = description,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }

                Column(
                    modifier = Modifier.fillMaxHeight(), // or fillMaxSize()
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (status == RequirementStatus.APPROVED) {
                            IconButton(
                                onClick = { /* View document */ },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_view),
                                    contentDescription = "View",
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            IconButton(
                                onClick = { /* Download document */ },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_download),
                                    contentDescription = "Download",
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        } else {
                            Button(
                                onClick = { /* Upload action */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = gradient_start
                                ),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_upload),
                                    contentDescription = "Upload",
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Upload",
                                    fontSize = 11.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}