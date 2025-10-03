package com.example.interna.ClientMain.signup

import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interna.R
import com.example.interna.ui.theme.gradient_start

@Composable
fun SlideThree() {
    var gmail by remember { mutableStateOf("") }
    var confirmGmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val confirmGmailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var showGmailError by remember { mutableStateOf(false) }
    var showConfirmGmailError by remember { mutableStateOf(false) }

    // New: show error if confirm password doesn't match password
    val showConfirmPasswordError = remember(password, confirmPassword) {
        confirmPassword.isNotEmpty() && confirmPassword != password
    }
    val showConfirmGmailMismatch = remember(gmail, confirmGmail) { // Add this
        confirmGmail.isNotEmpty() && confirmGmail != gmail
    }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var confirmEmailError by remember { mutableStateOf(false) } // Add this
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ){
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
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = "Secure Your Account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
                Text(
                    text = "After setting up your account, you will be able to login using your Email Account or your own Student Number. Whatever your preference!",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp)

                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 50.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Email Field
                    OutlinedTextField(
                        value = gmail,
                        onValueChange = {
                            gmail = it
                            showGmailError = false
                            emailError = false
                        },
                        label = { Text("Email") },
                        isError = showGmailError || emailError,
                        supportingText = {
                            Text(
                                text = when {
                                    emailError -> "Email field cannot be empty."
                                    showGmailError -> "Please enter a valid email address"
                                    else -> " "
                                },
                                color = if (showGmailError || emailError) MaterialTheme.colorScheme.error else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_mail),
                                contentDescription = "Email Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (gmail.isBlank()) {
                                    emailError = true
                                } else if (!Patterns.EMAIL_ADDRESS.matcher(gmail).matches()) {
                                    showGmailError = true
                                } else {
                                    passwordFocusRequester.requestFocus()
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    //confirm gmail
                    OutlinedTextField(
                        value = confirmGmail,
                        onValueChange = {
                            confirmGmail = it
                            showConfirmGmailError = false
                            confirmEmailError = false
                        },
                        label = { Text("Confirm Email") },
                        isError = showConfirmGmailError || confirmEmailError || showConfirmGmailMismatch,
                        supportingText = {
                            Text(
                                text = when {
                                    confirmEmailError -> "Confirm email field cannot be empty"
                                    showConfirmGmailMismatch -> "Email accounts do not match"
                                    else -> " "
                                },
                                color = if (showConfirmGmailError || confirmEmailError || showConfirmGmailMismatch)
                                    MaterialTheme.colorScheme.error
                                else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(confirmGmailFocusRequester),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_mail),
                                contentDescription = "Confirm Email Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (confirmGmail.isBlank()) {
                                    confirmEmailError = true
                                } else if (confirmGmail != gmail) {
                                    showConfirmGmailError = true
                                } else {
                                    passwordFocusRequester.requestFocus()
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

// Password Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                        },
                        label = { Text("Enter your new Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordFocusRequester),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Password Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (password.isBlank()) {
                                    passwordError = true
                                } else {
                                    confirmPasswordFocusRequester.requestFocus()
                                }
                            }
                        ),
                        supportingText = {
                            when {
                                passwordError -> Text(
                                    text = "Password field cannot be empty.",
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.height(20.dp)
                                )
                                password.isNotEmpty() -> Text(
                                    text = buildAnnotatedString {
                                        append("Password strength: ")
                                        withStyle(style = SpanStyle(color = getPasswordStrength(password).second)) {
                                            append(getPasswordStrength(password).first)
                                        }
                                    },
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.height(20.dp)
                                )
                                else -> Text(
                                    text = " ",
                                    color = Color.Transparent,
                                    modifier = Modifier.height(20.dp)
                                )
                            }
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

                    Spacer(modifier = Modifier.height(4.dp))

// Confirm Password Field
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            confirmPasswordError = false
                        },
                        label = { Text("Confirm Password") },
                        singleLine = true,
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(confirmPasswordFocusRequester),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Password Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (confirmPassword.isBlank()) {
                                    confirmPasswordError = true
                                } else if (password != confirmPassword) {
                                    confirmPasswordError = true
                                } else {
                                    focusManager.clearFocus()
                                }
                            }
                        ),
                        isError = confirmPasswordError || showConfirmPasswordError,
                        supportingText = {
                            Text(
                                text = when {
                                    confirmPasswordError -> "Confirm password field cannot be empty."
                                    showConfirmPasswordError -> "Passwords do not match"
                                    else -> " "
                                },
                                color = if (confirmPasswordError || showConfirmPasswordError)
                                    MaterialTheme.colorScheme.error
                                else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (confirmPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                                    ),
                                    contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(
                        onClick = {
                            val emailEmpty = gmail.isBlank()
                            val passwordEmpty = password.isBlank()
                            val confirmPasswordEmpty = confirmPassword.isBlank()
                            val emailInvalid = !emailEmpty && !Patterns.EMAIL_ADDRESS.matcher(gmail).matches()

                            emailError = emailEmpty
                            passwordError = passwordEmpty
                            confirmPasswordError = confirmPasswordEmpty
                            showGmailError = emailInvalid

                            if (!emailEmpty && !emailInvalid && !passwordEmpty && !confirmPasswordEmpty) {
                                // TODO: Proceed with navigation or logic
                            }
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
                        Text(text = "Next")
                    }


                }
            }
        }
    }
}

fun getPasswordStrength(password: String): Triple<String, Color, Float> {
    val length = password.length
    val letterCount = password.count { it.isLetter() }
    val digitCount = password.count { it.isDigit() }
    val symbolCount = password.count { !it.isLetterOrDigit() }

    val strong = (letterCount >= 10 && digitCount == 0 && symbolCount == 0) ||
            (letterCount >= 8 && digitCount >= 2) ||
            (letterCount >= 9 && digitCount >= 1) ||
            (letterCount >= 5 && digitCount >= 2 && symbolCount >= 1) ||
            (length > 9)

    val medium = !strong && length >= 8 && letterCount >= 5 && (digitCount >= 1 || symbolCount >= 1)

    return when {
        strong -> Triple("Strong", Color(0xFF2E7D32), 1f)      // Full green bar
        medium -> Triple("Medium", Color(0xFFF9A825), 0.6f)   // Yellow bar (60%)
        else -> Triple("Weak", Color.Red, 0.3f)               // Red bar (30%)
    }
}
