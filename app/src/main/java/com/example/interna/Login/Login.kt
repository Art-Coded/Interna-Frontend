package com.example.interna.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R
import com.example.interna.ui.theme.gradient_start
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(homeClick: () -> Unit, schoolClick: () -> Unit) {
    val scope = rememberCoroutineScope()

    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var hasSubmittedStudentId by remember { mutableStateOf(false) }
    val isStudentIdValid = remember(studentId) {
        studentId.matches(Regex("^[A-Za-z0-9._%+-]+@(gmail|yahoo)\\.com$"))
    }

    val passwordFocusRequester = remember { FocusRequester() }
    var passwordVisible by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        }) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Login with",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize =28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = 34.dp, top = 16.dp)

            )
            Row{
                Text(
                    text = "Interna",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize =48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(start = 34.dp, top = 10.dp)

                )
                //this will be the app's logo next time
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(68.dp)
                        .padding(start = 8.dp, top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically, // ✅ aligns them nicely
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.schoollogo),
                            contentDescription = "School Logo",
                            modifier = Modifier.height(70.dp).padding(bottom = 8.dp)
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(gradient_start)
                                .clickable {
                                    scope.launch {
                                        schoolClick()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "add school",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp)) // ✅ space before logo

                    OutlinedTextField(
                        value = studentId,
                        onValueChange = {
                            studentId = it
                            if (hasSubmittedStudentId) hasSubmittedStudentId = false
                        },
                        label = { Text("Student-ID") },
                        isError = hasSubmittedStudentId && !isStudentIdValid,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                hasSubmittedStudentId = true
                                if (isStudentIdValid) {
                                    passwordFocusRequester.requestFocus()
                                }
                            }
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "password icon"
                            )
                        },
                        supportingText = {
                            Text(
                                text = if (hasSubmittedStudentId && !isStudentIdValid) "Please enter a valid email." else " ",
                                color = if (hasSubmittedStudentId && !isStudentIdValid) MaterialTheme.colorScheme.error else Color.Transparent
                            )
                        },
                        modifier = Modifier
                            .weight(1f) // ✅ takes up remaining space only
                    )
                }


                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "Password Icon"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                                ),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var rememberMeChecked by remember { mutableStateOf(false) }

                    Checkbox(
                        checked = rememberMeChecked,
                        onCheckedChange = { rememberMeChecked = it }
                    )
                    Text(text = "Remember me", fontSize = 14.sp)
                }

                Text(
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        // TODO: Forgot password logic
                    }
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    homeClick()// TODO: Navigate to Login
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = gradient_start,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = "Login")
            }

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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account?",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp))

                Balloon(
                    builder = builder,
                    balloonContent = {
                        Text("Please contact your assigned\nOJT adviser for your account", fontSize = 14.sp, color = MaterialTheme.colorScheme.background)
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


            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Developed by Art",
                fontSize = 14.sp,
                modifier = Modifier
                    .alpha(0.5f)
            )
        }
    }
}