package com.example.interna.ClientMain.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SlideFour(navController: NavController) {

    Button(
        onClick = { navController.navigate("BottomNav") },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Text("Next")
    }
}