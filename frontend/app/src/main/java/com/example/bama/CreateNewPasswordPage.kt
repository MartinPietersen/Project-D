package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
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
fun CreateNewPassword(navController: NavHostController = rememberNavController()) { // This page is the create new password page where the user can create a new password
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {},
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
                Spacer(modifier = Modifier.height(25.dp))
                EnterNewPasswordSection()
                Spacer(modifier = Modifier.height(70.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    EnterNewPasswordForm()
                }
                Spacer(modifier = Modifier.height(150.dp))
                CreateNewPasswordActions(SaveClicked = { /*TODO*/ })
            }
        },
        bottomBar = {}
    )
}

@Composable
fun EnterNewPasswordSection() {
    Column {
        Text(
            "Nieuw wachtwoord",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            "Vul hieronder een nieuw wachtwoord in voor uw account", style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EnterNewPasswordForm() { // This is the form where the user can enter their new password
    var wachtwoord by remember { mutableStateOf("") }
    var wachtwoordAck by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Wachtwoord", color = GrayDark, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default)
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = wachtwoord,
            onValueChange = { wachtwoord = it },
            placeholder = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Wachtwoord",
                        tint = GrayDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Vul uw nieuwe wachtwoord in", color = GrayDark)
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
            value = wachtwoordAck,
            onValueChange = { wachtwoordAck = it },
            placeholder = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Wachtwoord",
                        tint = GrayDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Vul uw wachtwoord nog een keer in", color = GrayDark)
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
fun CreateNewPasswordActions(SaveClicked: () -> Unit) { // This is the button that the user can click to save their new password
    Column {
        Button(
            onClick = SaveClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Green, contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Opslaan", fontSize = 28.sp)
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