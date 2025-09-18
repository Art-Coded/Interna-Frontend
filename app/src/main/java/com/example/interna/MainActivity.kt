package com.example.interna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.interna.ui.theme.InternaTheme
//FRONTEND INTERNA
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InternaTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboarding",
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = { fadeIn(animationSpec = tween(200)) },
                        exitTransition = { fadeOut(animationSpec = tween(200)) }
                    ) {
//                        composable("onboarding") {
//                            OnboardingScreen(
//                                onLoginClick = { navController.navigate("login") {popUpTo("onboarding") {inclusive = true}} },
//                                signupClick = { navController.navigate("signup") {popUpTo("onboarding") {inclusive = true}} }
//
//                            )
//                        }
//                        composable("login") {
//                            LoginScreen(
//                                onBoardingClick = { navController.navigate("onboarding") {popUpTo("login") {inclusive = true}} },
//                                signupClick = { navController.navigate("signup") {popUpTo("login") {inclusive = true}} },
//                                homeClick = { navController.navigate("BottomNav") {popUpTo("login") {inclusive = true}} }
//                            )
//                        }
//                        composable("signup") {
//                            SignupScreen(
//                                loginClick = { navController.navigate("login") {popUpTo("signup") {inclusive = true}} },
//                                loginClick1 = { navController.navigate("login") {popUpTo("signup") {inclusive = true}} }
//                            )
//                        }
//                        composable("BottomNav") {
//                            BottomNavScreen()
//                        }

                    }
                }
            }
        }
    }
}

