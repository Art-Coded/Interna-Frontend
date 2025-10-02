package com.example.interna.ClientMain.signup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import androidx.navigation.NavController
import com.example.interna.R
import com.example.interna.ui.theme.blue_green
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor

@Composable
fun IntroScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Text(
                text = "Welcome to Interna!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            val background = MaterialTheme.colorScheme.onSurface

            val builder = rememberBalloonBuilder {
                setArrowSize(10)
                setArrowPosition(0.5f)
                setCornerRadius(8f)
                setPadding(12)
                setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                setBackgroundColor(background)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Before you begin your Internship, we would like to collect your data first.",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.weight(1f) // <-- allow balloon to show beside
                )

                Balloon(
                    builder = builder,
                    balloonContent = {
                        Text(
                            "We are committed to protecting your data. Your credentials are securely stored and NEVER shared with third parties. Only to your Internship Advisors to properly monitor you accurately.",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.background,
                            lineHeight = 16.sp
                        )
                    }
                ) { balloonWindow ->
                    IconButton(
                        onClick = { balloonWindow.showAlignTop() },
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 6.dp)
                            .alpha(0.8f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
                            contentDescription = "Info"
                        )
                    }
                }
            }


            val context = LocalContext.current
            val defaultTextColor = MaterialTheme.colorScheme.onBackground

            val annotatedText = buildAnnotatedString {
                append("By signing up, you agree to our ")

                pushStringAnnotation(tag = "URL", annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX")
                withStyle(style = SpanStyle(color = blue_green, textDecoration = TextDecoration.Underline)) {
                    append("Terms of Service")
                }
                pop()

                append(" and ")

                pushStringAnnotation(tag = "URL", annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX")
                withStyle(style = SpanStyle(color = blue_green, textDecoration = TextDecoration.Underline)) {
                    append("Privacy Policy")
                }
                pop()
            }

            var layoutResult: TextLayoutResult? = null

            Text(
                text = annotatedText,
                style = TextStyle(fontSize = 14.sp, color = defaultTextColor, textAlign = TextAlign.Center),
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
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                        context.startActivity(intent)
                                    }
                            }
                        }
                    },
                onTextLayout = { result ->
                    layoutResult = result
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("BottomNav") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Text("Next")
            }
        }
    }
}