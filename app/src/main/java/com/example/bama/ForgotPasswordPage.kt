package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
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


@Composable
@Preview
fun ForgotPasswordPage(navController: NavHostController = rememberNavController()) {

    BackgroundCanvas()
    BackButtonForgotPassword(onBackClick = { /*TODO*/ })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 150.dp, 0.dp, 0.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .padding(26.dp, 10.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        ForgotPasswordSection()

        Spacer(modifier = Modifier.height(100.dp))
        // I want to change arrangement to centered at this point
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            EnterEmailForm()
        }

        Spacer(modifier = Modifier.height(150.dp))

        ForgotPasswordActions(sendEmailClicked = { /*TODO*/ } )
    }
}

@Composable
fun ForgotPasswordSection() {
    Column {
        Text(
            "Wachtwoord vergeten",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
//            modifier = Modifier.padding(16.dp)
        )
        Text(
            "Vul hieronder uw email in voor een nieuw wachtwoord", style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EnterEmailForm() {
    var email by remember { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = GrayDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Vul uw email-adres in", color = GrayDark
                    )
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
fun ForgotPasswordActions(sendEmailClicked: () -> Unit) {
    Column {
        Button(
            onClick = sendEmailClicked, colors = ButtonDefaults.buttonColors(
                containerColor = Green, contentColor = Color.White
            ), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Verstuur e-mail", fontSize = 28.sp
            )
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
            Text(
                text = "Hulp nodig", fontSize = 28.sp
            )
        }
    }
}

@Composable
fun BackButtonForgotPassword(onBackClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onBackClick)
                .padding(8.dp)
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
                color = Color.White,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 1.sp
            )
        }
    }
}