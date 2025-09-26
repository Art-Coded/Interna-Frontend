package com.example.interna.ClientMain.WeeklyReport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.ClientMain.WeeklyReport.ReportWriting.ReportWritingCard
import com.example.interna.ClientMain.WeeklyReport.WeeklyProgress.WeeklyProgressCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyReportScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 12.dp)
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
                WeeklyProgressCard()

                ReportWritingCard()

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

