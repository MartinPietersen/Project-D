package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.navigation.compose.rememberNavController
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.WhiteBroken

@Composable
@Preview
fun CreateNewAccountTwo(navController: NavHostController = rememberNavController()) { // This page is the second page of the create new account process where the user can enter their email and password
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            BackButtonCreateAccountTwo(navController::popBackStack)
        },
        content = {
            val padding = it
            BackgroundCanvas()
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmedPassword by remember { mutableStateOf("") }
            val passwordsMatch by remember { derivedStateOf { password == confirmedPassword } }
            var showError by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }

            if (showError) {
                ErrorDialogTwo(
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
                Spacer(modifier = Modifier.height(10.dp))
                CreateNewAccountSectionTwo()
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    EnterDataFormTwo(
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        confirmedPassword = confirmedPassword,
                        onConfirmedPasswordChange = { confirmedPassword = it },
                        passwordsMatch = passwordsMatch
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                val isFormValid = email.isNotBlank() && password.isNotBlank() && confirmedPassword.isNotBlank() && passwordsMatch
                CreateAccountActionsTwo(
                    isFormValid = isFormValid,
                    CreateClicked = { // Check if the email and password are valid
                        if (isFormValid) {
                            if (!isValidEmail(email)) {
                                showError = true
                                errorMessage = "Incorrect email adres"
                            }
                            else if (!isValidPassword(password)) {
                                showError = true
                                errorMessage = "Wachtwoord moet minimaal 12 tot 30 karakters bevatten en minimaal 1 kleine letter, 1 grote letter, 1 speciaal karakter en 1 cijfer."
                            }
                            else {
                                // Navigate to the next page
                                navController.navigate(BamaScreens.Login.name)
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {}
    )
}

@Composable
fun CreateNewAccountSectionTwo() {
    Column {
        Text(
            "Hallo daar",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            "Hieronder kunt u een account aanmaken", style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EnterDataFormTwo( // This is the form where the user can enter their email and password
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmedPassword: String,
    onConfirmedPasswordChange: (String) -> Unit,
    passwordsMatch: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Email", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            placeholder = {
                Row {
                    Text("Vul uw email in", color = Color.Black)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
        Text(text = "Wachtwoord", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Row {
                    Text("Vul uw wachtwoord in", color = Color.Black)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
        Text(text = "Bevestig uw wachtwoord", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = confirmedPassword,
            onValueChange = onConfirmedPasswordChange,
            placeholder = {
                Row {
                    Text("Vul uw wachtwoord nog een keer in", color = Color.Black)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
        if (password.isNotBlank() || confirmedPassword.isNotBlank()) { // Check if the passwords match
            Text(
                text = if (passwordsMatch) "Wachtwoorden komen overeen" else "Wachtwoorden komen niet overeen",
                color = if (passwordsMatch) Color.Green else Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun CreateAccountActionsTwo(isFormValid: Boolean, CreateClicked: () -> Unit) {
    Column {
        Button(
            onClick = CreateClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Green else Color.Gray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text(text = "Aanmaken", fontSize = 28.sp)
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
fun BackButtonCreateAccountTwo(onBackClick: () -> Unit) {
    Button(
        modifier = Modifier.background(Green, shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)).padding(top = 8.dp),
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
fun ErrorDialogTwo(errorMessage: String, onDismiss: () -> Unit) { // This is the dialog that is displayed when the user enters an invalid email or password
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

fun isValidEmail(email: String): Boolean { // Check if the email is valid
    val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}${'$'}""")
    return emailRegex.matches(email)
}

fun isValidPassword(password: String): Boolean { // Check if the password is valid
    val passwordRegex = Regex("""^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,30}${'$'}""")
    return passwordRegex.matches(password)
}
