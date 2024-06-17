package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun CreateNewAccount(navController: NavHostController = rememberNavController()) { // This page is the first page of the create new account process where the user can enter their first name, last name and age
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            BackButtonCreateAccount(navController::popBackStack)
        },
        content = {
            val padding = it
            BackgroundCanvas()
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var age by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
            var showError by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }

            if (showError) {
                ErrorDialog(
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
                CreateNewAccountSection()
                Spacer(modifier = Modifier.height(25.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    EnterDataForm(
                        firstName = firstName,
                        onFirstNameChange = { firstName = it },
                        lastName = lastName,
                        onLastNameChange = { lastName = it },
                        age = age,
                        onAgeChange = { age = it },
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && age.isNotBlank()
                CreateAccountActions( // This is the button that the user can click to go to the next page, and where is checked if the form is valid
                    isFormValid = isFormValid,
                    NextClicked = {
                        if (isFormValid) {
                            if (firstName.all { it.isLetter() } && lastName.all { it.isLetter() }) {
                                // Navigate to the next page
                                navController.navigate(BamaScreens.CreateNewAccountTwo.name)
                            } else {
                                showError = true
                                errorMessage = "Voornaam en achternaam mogen alleen letters bevatten"
                            }
                        } else {
                            showError = true
                            errorMessage = "Alle velden moeten worden ingevuld"
                        }
                    }
                )
            }
        },
        bottomBar = {}
    )
}

@Composable
fun CreateNewAccountSection() {
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
fun EnterDataForm( // This is the form where the user can enter their first name, last name and age
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Voornaam", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = onFirstNameChange,
            placeholder = {
                Row {
                    Text("Vul uw voornaam in", color = Color.Black)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
        Text(text = "Achternaam", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = onLastNameChange,
            placeholder = {
                Row {
                    Text("Vul uw achternaam in", color = Color.Black)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GrayLight,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = GrayLight,
                focusedContainerColor = Color.White
            )
        )
        Text(text = "Leeftijd", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = age,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onExpandedChange(true) },
                placeholder = { Text(text = "00", color = Color.Black) },
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { onExpandedChange(true) }
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GrayLight,
                    unfocusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = GrayLight,
                    focusedContainerColor = Color.White
                )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .background(Color.White)
                    .width(55.dp)
            ) {
                (1..120).forEach { number ->
                    DropdownMenuItem(
                        onClick = {
                            onAgeChange(number.toString())
                            onExpandedChange(false)
                        },
                        text = { Text(text = number.toString(), fontSize = 16.sp) }
                    )
                }
            }
        }
    }
}

@Composable
fun CreateAccountActions(isFormValid: Boolean, NextClicked: () -> Unit) {
    Column {
        Button(
            onClick = NextClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Green else Color.Gray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text(text = "Volgende", fontSize = 28.sp)
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
fun BackButtonCreateAccount(onBackClick: () -> Unit) {
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
fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) { // This is the dialog that is displayed when the user has entered invalid data
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
