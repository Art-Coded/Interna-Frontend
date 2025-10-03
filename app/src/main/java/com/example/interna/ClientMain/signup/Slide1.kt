package com.example.interna.ClientMain.signup

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.commandiron.compose_loading.ChasingDots
import com.commandiron.compose_loading.WanderingCubes
import com.example.interna.R
import com.example.interna.ui.theme.blue_green
import com.example.interna.ui.theme.gradient_start
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor

@Composable
fun SlideOne() {
    val scrollState = rememberScrollState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.home_intro))
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(composition) {
        if (composition != null) {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            ChasingDots(modifier = Modifier.size(64.dp).padding(bottom = 30.dp))
        }
    } else {
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

                AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(animationSpec = tween(durationMillis = 5200)) +
                            slideInVertically(
                                animationSpec = tween(durationMillis = 5200),
                                initialOffsetY = { fullHeight -> fullHeight / 4 }
                            )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier.size(320.dp)
                        )

                        Text(
                            text = "Welcome to Interna!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 34.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 12.dp),
                        )

                        Text(
                            text = "Before you begin your Internship, we would like to collect your school's Internship requirements first. Would you like to proceed?",
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current
                val defaultTextColor = MaterialTheme.colorScheme.onBackground

                val annotatedText = buildAnnotatedString {
                    append("By signing up, you agree to our ")

                    pushStringAnnotation(
                        tag = "URL",
                        annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = gradient_start,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Terms of Service")
                    }
                    pop()

                    append(" and ")

                    pushStringAnnotation(
                        tag = "URL",
                        annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = gradient_start,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Privacy Policy")
                    }
                    pop()
                }

                var layoutResult: TextLayoutResult? = null

                Text(
                    text = annotatedText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = defaultTextColor,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures { tapOffset ->
                                layoutResult?.let { layout ->
                                    val position = layout.getOffsetForPosition(tapOffset)
                                    annotatedText
                                        .getStringAnnotations("URL", position, position)
                                        .firstOrNull()?.let { annotation ->
                                            val intent =
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(annotation.item)
                                                )
                                            context.startActivity(intent)
                                        }
                                }
                            }
                        },
                    onTextLayout = { result ->
                        layoutResult = result
                    }
                )

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
                    Text(text = "Proceed")
                }
            }
        }

    }
}