package com.example.bama

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.Green
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONException
import org.json.JSONObject





@Composable
fun BackgroundCanvas() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF107200))
    ) {
        OverlayedOvals()
    }
}

@Composable
fun OverlayedOvals() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Big circle
        drawOval(
            color = Color(0xFFFFFFFF).copy(alpha = 0.25f), topLeft = Offset(
                x = size.width - 100.dp.toPx(), y = -50.dp.toPx()
            ), size = Size(
                width = 200.dp.toPx(), height = 200.dp.toPx()
            )
        )

        // Small circle above the big circle
        drawOval(
            color = Color(0xFFFFFFFF).copy(alpha = 0.25f), topLeft = Offset(
                x = size.width - 150.dp.toPx(), y = -90.dp.toPx()
            ), size = Size(
                width = 130.dp.toPx(), height = 130.dp.toPx()
            )
        )
    }
}

private fun SendLoginApiRequest(email: String, password: String, callback: (Boolean) -> Unit) {
    val client = OkHttpClient()

    val jsonObject = JSONObject()
    try {
        jsonObject.put("email", email)
        jsonObject.put("password", password)
    } catch (e: JSONException) {
        e.printStackTrace()
        callback(false)
        return
    }
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val body = jsonObject.toString().toRequestBody(mediaType)

    val request = Request.Builder()
        .url("https://api.bama-hsr-server.org/login")
        .post(body)
        .build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            Handler(Looper.getMainLooper()).post {
                callback(false)
            }
        }
        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful){
                    Handler(Looper.getMainLooper()).post {
                        callback(false)
                    }
                    return
                }

                Handler(Looper.getMainLooper()).post {
                    callback(true)
                }
            }
        }
    })
}


@Composable
fun RememberButton() {
    var rememberMe by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Onthoud mij voor de volgende keer",
            color = Color.Black,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )


        Switch(checked = rememberMe,
            onCheckedChange = { rememberMe = it },
            colors = SwitchDefaults.colors(
                checkedTrackColor = Green,

                ),
            modifier = Modifier
                .graphicsLayer { scaleX = 1.2f; scaleY = 1.1f }
                .padding(0.dp, 0.dp, 10.dp, 0.dp))
    }
}

@Composable
fun LoginForm(email: String, onEmailChange: (String) -> Unit, password: String, onPasswordChange: (String) -> Unit) { // This is the form where the user can enter their email and password

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
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Wachtwoord",
                        tint = GrayDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Vul uw wachtwoord in", color = GrayDark
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
fun LoginPage(navController: NavController) { // This is the login page where the user can login
    BackgroundCanvas()
    TopNavigationBar()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginFailed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 150.dp, 0.dp, 0.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .padding(26.dp, 10.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))


        WelcomeTextSection()
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            LoginForm(email = email, onEmailChange = { email = it }, password = password, onPasswordChange = { password = it })
        }
        Spacer(modifier = Modifier.height(5.dp))

        RememberButton()

        Spacer(modifier = Modifier.height(20.dp))

        if (loginFailed) {
            Text("Login failed. Please try again.", color = Color.Red, fontSize = 16.sp)
        }


        LoginActions(navController, email, password, onLoginResult = { success ->
            if (success) {
                navController.navigate(BamaScreens.HomePage.name)
            } else {
                loginFailed = true
            }
        })


        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            SignUpPrompt(navController)
        }
    }
}


@Composable
fun TopNavigationBar() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(26.dp)
    ) {
        IconButton(
            onClick = { System.exit(0) }, modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(25.dp)
        )
        Text(
            text = "Inloggen",
            color = Color.White,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun WelcomeTextSection() {
    Column {
        Text(
            "Welkom terug",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
//            modifier = Modifier.padding(16.dp)
        )
        Text(
            "Hallo daar, log in om verder te gaan", style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun LoginActions(navController: NavController, email: String, password: String, onLoginResult: (Boolean) -> Unit) { // This is the section where the user can login
    Column {
        TextButton(
            onClick = { navController.navigate(BamaScreens.ForgotPasswordPage.name) },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Wachtwoord vergeten?", fontSize = 16.sp, color = Green
            )
        }
        Button(
            onClick = {
                SendLoginApiRequest(email, password) { success ->
                    onLoginResult(success)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green, contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Inloggen", fontSize = 28.sp
            )
        }
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = { /*ToDo*/ },
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
fun SignUpPrompt(navController: NavController) { // This is the section where the user can sign up
    val interactionSource = remember { MutableInteractionSource() }
    TextButton(
        onClick = { navController.navigate(BamaScreens.CreateNewAccount.name) },
        modifier = Modifier.indication(interactionSource, indication = null)

    ) {
        Text(
            text = "Nog geen account?",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = "Meld je aan!", color = Green, fontSize = 16.sp
        )
    }
}


@Composable
fun ActivityButton(activity: Activity, color: Color) { // This is the button that is displayed for each activity
    Box(
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
                .fillMaxWidth()
                .aspectRatio(1f),

            shape = RoundedCornerShape(5),
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            )
        ) {
            Text(
                text = activity.name,
                modifier = Modifier.padding(8.dp),
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}