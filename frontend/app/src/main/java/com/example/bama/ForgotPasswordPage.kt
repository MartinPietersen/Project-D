package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.WhiteBroken

@Composable
fun ForgotPasswordPage(navController: NavHostController) { // This page is the forgot password page where the user can reset their password
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            BackButtonForgotPassword(navController::popBackStack)
        },
        content = {
            val padding = it
            BackgroundCanvas()
            var email by remember { mutableStateOf("") }
            var showError by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }

            if (showError) {
                ErrorDialogPage(
                    errorMessage = errorMessage,
                    onDismiss = { showError = false }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                    .padding(26.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                ForgotPasswordSection()
                Spacer(modifier = Modifier.height(100.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    EnterEmailForm(
                        email = email,
                        onEmailChange = { email = it }
                    )
                }
                Spacer(modifier = Modifier.height(150.dp))
                ForgotPasswordActions(
                    sendEmailClicked = {
                        if (!isValidEmailFormat(email)) {
                            showError = true
                            errorMessage = "Incorrect email adres"
                        } else {
                            // Handle send email logic here
                        }
                    }
                )
            }
        },
        bottomBar = {}
    )
}

@Composable
fun ForgotPasswordSection() {
    Column {
        Text(
            "Wachtwoord vergeten",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            "Vul hieronder uw email in voor een nieuw wachtwoord", style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EnterEmailForm(
    email: String,
    onEmailChange: (String) -> Unit
) { // This is the form where the user can enter their email
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            placeholder = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = GrayDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Vul uw email-adres in", color = GrayDark)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
    }
}

@Composable
fun ForgotPasswordActions(sendEmailClicked: () -> Unit) { // This is the button that the user can click to send an email
    Column {
        Button(
            onClick = sendEmailClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Green, contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Verstuur e-mail", fontSize = 28.sp)
        }
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = { /*TODO pop up screen met tekst hulp*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(2.dp, Green, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            Text(text = "Hulp nodig", fontSize = 28.sp)
        }
    }
}

@Composable
fun BackButtonForgotPassword(onBackClick: () -> Unit) {
    Button(
        modifier = Modifier
            .background(Green, shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .padding(top = 8.dp),
        onClick = onBackClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green, contentColor = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Terug",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun ErrorDialogPage(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        title = {
            Text(text = "Foutmelding")
        },
        text = {
            Text(text = errorMessage)
        }
    )
}

fun isValidEmailFormat(email: String): Boolean {
    val emailRegex = Regex("""[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}""")
    return emailRegex.matches(email)
}
