package com.example.interna.ClientMain.Home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R
import kotlinx.coroutines.launch

@Composable
fun QuickActionsCard() {
    var isExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animated expand/collapse button
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { isExpanded = !isExpanded },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = if (isExpanded) R.drawable.ic_arrow_up
                    else R.drawable.ic_arrow_down
                ),
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = if (isExpanded) "Collapse" else "Expand",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    // Smoothly animated expandable content
    AnimatedVisibility(
        visible = isExpanded,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(durationMillis = 300)
        ) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(420.dp)
                ,
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quick Actions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    IconButton(
                        onClick = {
                            scope.launch {

                            }
                        },
                        modifier = Modifier
                            .size(40.dp) // ensures a good touch target
                            .alpha(0.7f)
                            .clip(CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tutorial),
                            contentDescription = "Menu Icon",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.height(24.dp) // size of the icon inside
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // 1. Request Absence (I’m absent today)
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFFF9800))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sick),
                            contentDescription = "Absent Today",
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Request Absence", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Request Time Correction
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFF2196F3))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "Time Correction",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Request Time Correction", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Row 3 - Request Offline Clock-In
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFFF9800))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_change),
                            contentDescription = "Change Work Time",
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Change Work Time", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 4. Request Leave
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFF4CAF50))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_overtime),
                            contentDescription = "Request Overtime",
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Request Overtime", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 5. FAQs
                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFF9C27B0))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "FAQs",
                            tint = Color(0xFF9C27B0),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("FAQs", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}