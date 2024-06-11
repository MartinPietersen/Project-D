package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bama.models.Message
import com.example.bama.ui.theme.Gray
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.GrayLighter
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.NavBarButtons
import com.example.bama.ui.theme.WhiteBroken
import com.example.bama.ui.theme.drawOvalsBehind


@Preview
@Composable
fun ChatPage(navController: NavHostController = rememberNavController()) {
    var messages by remember { mutableStateOf(populateChatMessages()) }
    var lastSent by remember { mutableStateOf("") }
    var chatMessage by remember { mutableStateOf("") }

    WhiteBackGround()
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(GrayLighter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = Green,
                            shape = RoundedCornerShape(bottomEnd = 55.dp)
                        )
                        .drawOvalsBehind()
                ) {
                    Row() {
                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(10.dp, 10.dp, 0.dp, 0.dp)
                                .wrapContentSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green,
                                contentColor = WhiteBroken
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Exit",
                                tint = WhiteBroken,
                                modifier = Modifier
                                    .padding(0.dp)
                                    .size(32.dp)
                            )
                            Text(
                                text = "Vorige",
                                color = WhiteBroken,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                    }
                }
            }
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding()
                            .background(GrayLighter)
                            .padding(0.dp, 0.dp, 0.dp, 8.dp)
                    ) {
                        Text(
                            text = "Buurtsportcoach",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(0.dp, 16.dp, 16.dp, 0.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(GrayDark)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding()
                ) {
                    // Chat messages
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 2.dp, 0.dp, 60.dp)
                    ) {
                        items(messages.size) { index ->
                            if (messages[index].sender == "Buurtsportcoach") {
                                ReceivedChatMessage(lastSent, messages[index].message)
                            } else {
                                SentChatMessage(messages[index].message)
                            }
                            lastSent = messages[index].sender
                        }
                    }

                    // Message input field
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding()
                            .align(Alignment.BottomCenter)
                    ) {
                        // Gradient background above text input field
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            WhiteBroken.copy(alpha = 0.4f),
                                            GrayLighter.copy(alpha = 0.8f),
                                            Gray
                                        )
                                    )
                                )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding()
                                .background(WhiteBroken)
                        ) {
                            TextField(
                                value = chatMessage,
                                onValueChange = { chatMessage = it },
                                label = { Text("Typ een bericht...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = WhiteBroken,
                                    unfocusedContainerColor = WhiteBroken,
                                    focusedIndicatorColor = GrayLight,
                                    unfocusedIndicatorColor = Transparent,
                                    cursorColor = GrayLight,
                                    focusedTextColor = Black,
                                    unfocusedTextColor = GrayDark,
                                    focusedLabelColor = GrayDark,
                                    unfocusedLabelColor = GrayDark
                                ),
                            )

                            Button(
                                onClick = {
                                    if (chatMessage.isNotBlank()) {
                                        messages = messages + Message(chatMessage, "John", "12:00")
                                        chatMessage = ""
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(Alignment.CenterEnd)
                                    .height(50.dp)
                                    .aspectRatio(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Green,
                                    contentColor = GrayDark
                                ),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Send",
                                    tint = WhiteBroken,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .height(60.dp)
                                        .aspectRatio(1f)
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
//            NavBarButtons(navController)
        }
    )
}

fun populateChatMessages(): List<Message> {
    return listOf(
        Message(
            "Hallo John, Doe je volgende week Dinsdag mee met de wandeling?",
            "Buurtsportcoach",
            "12:00"
        ),
        Message("Ja, ik doe mee! Hoe laat is het?", "John", "12:01"),
        Message("Het is op dinsdag om 10:00 uur.", "Buurtsportcoach", "12:02"),
        Message("Ik ben erbij!", "John", "12:03"),
        Message(
            "Hallo John, Ik kreeg een melding dat u al een aantal weken niet heeft deelgenomen aan activiteiten.",
            "Buurtsportcoach",
            "12:00"
        ),
        Message(
            "Hallo, ik ben op vakantie geweest en heb daardoor niet kunnen deelnemen aan de activiteiten.",
            "John",
            "12:01"
        ),
        Message(
            "Dat is geen probleem, ik zal u op de hoogte houden van de activiteiten.",
            "Buurtsportcoach",
            "12:02"
        ),
        Message("Dank u wel, ik kijk er naar uit om weer deel te nemen.", "John", "12:03"),
    )
}


@Composable
fun ReceivedChatMessage(lastSent: String, message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 0.dp, 0.dp, 8.dp)
    )
    {

        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Gray,
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
//            .align(Alignment.CenterVertically)
                .width(200.dp)
                .wrapContentHeight()
                .background(
                    color = GrayLighter,
                    shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
                )
        )
        {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}

fun addMessage(message: String, messages: MutableList<Message>): MutableList<Message> {
    messages.add(Message(message, "John", "12:00"))
    return messages
}

@Composable
fun SentChatMessage(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 0.dp, 0.dp, 8.dp),
        horizontalArrangement = Arrangement.End
    )
    {

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
//            .align(Alignment.CenterVertically)
                .width(200.dp)
                .wrapContentHeight()
                .background(
                    color = Green,
                    shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
                )
        )
        {
            Text(
                text = message,
                color = WhiteBroken,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}