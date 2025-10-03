package com.example.interna.ClientMain.signup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.interna.R
import com.example.interna.Reusables.PagerIndicator
import com.example.interna.ui.theme.blue_green
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor

@Composable
fun IntroScreen(navController: NavController) {

    val pageCount = 4
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
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
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                IconButton(
                    onClick = { /* TODO: opens alert dialog */ },
                    modifier = Modifier.size(46.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }

                // Center logo using weight
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.internalogo),
                        contentDescription = "Interna Logo",
                        modifier = Modifier.height(44.dp).padding(end = 15.dp),
                        colorFilter = ColorFilter.tint(
                            if (isDarkTheme) MaterialTheme.colorScheme.onBackground
                            else MaterialTheme.colorScheme.onBackground
                        )
                    )
                }

                // Balloon info button pinned right
                val background = MaterialTheme.colorScheme.onSurface
                val builder = rememberBalloonBuilder {
                    setArrowSize(0)
                    setCornerRadius(8f)
                    setMarginTop(6)
                    setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                    setBackgroundColor(background)
                }

                Balloon(
                    builder = builder,
                    balloonContent = {
                        Text(
                            "We are committed to protecting your data. Your credentials are securely stored and NEVER shared with third parties. Only to your Internship Advisors to properly monitor you accurately.",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.background,
                            lineHeight = 16.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                ) { balloonWindow ->
                    IconButton(
                        onClick = { balloonWindow.showAlignTop() },
                        modifier = Modifier.size(32.dp).padding(end = 10.dp) // keep consistent size
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info),
                            contentDescription = "Info"
                        )
                    }
                }
            }


            // Pager indicator
            PagerIndicator(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Pager for slides
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> SlideOne()
                    1 -> SlideTwo()
                    2 -> SlideThree()
                    3 -> SlideFour(navController = navController)
                }
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