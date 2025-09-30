package com.example.interna.ClientMain.Home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.interna.ClientMain.Home.AttendanceHistory.AttendanceHistoryCard
import com.example.interna.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.internalogo),
                        contentDescription = "Interna Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .height(6.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Interna",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )

                    Spacer(modifier = Modifier.weight(1f))

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
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "Menu Icon",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.height(24.dp) // size of the icon inside
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileCard()

                AttendanceCard()

                AttendanceHistoryCard(navController = navController)

                ProgressBreakdownCard()

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

//Monitoring Sheet input field requirements

//Sex M or F (Check box)
//Complete Name of Host/Company  (HTE) (Input field)
//Complete Address of HTE (input field)
//MOA   Notarized and Submitted to the SIPP Coordinator|Still Processing|None (Check boxes among those 3)
//Regional Location of the HTE (Input field)
//Start of Internship (date picker)
//How many hours of internship to render (input field in numbers)
//Name of Manager/Immediate Supervisor (Input field)
//Manager/Immediate Supervisor Contact Number (input number)

//Pin Your Workplace (Google maps API drag and drop with coordinates display)
//Work Schedule (Set work days per week and work hours per day)
