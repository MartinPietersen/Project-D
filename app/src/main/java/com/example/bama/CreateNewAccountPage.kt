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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CreateNewAccount(navController: NavHostController = rememberNavController()) {
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            BackButtonCreateAccount(navController::popBackStack)
        },
        content = {
            val padding = it
            BackgroundCanvas()
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
                    EnterDataForm()
                }
                Spacer(modifier = Modifier.height(30.dp))
                CreateAccountActions(NextClicked = { /*TODO*/ })
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
fun EnterDataForm() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

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
            onValueChange = { firstName = it },
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
            onValueChange = { lastName = it },
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
                    .clickable { expanded = true },
                placeholder = { Text(text = "00", color = Color.Black) },
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = true }
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
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .width(55.dp)
            ) {
                (1..120).forEach { number ->
                    DropdownMenuItem(
                        onClick = {
                            age = number.toString()
                            expanded = false
                        },
                        text = { Text(text = number.toString(), fontSize = 16.sp) }
                    )
                }
            }
        }
    }
}

@Composable
fun CreateAccountActions(NextClicked: () -> Unit) {
    Column {
        Button(
            onClick = NextClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Green, contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
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
