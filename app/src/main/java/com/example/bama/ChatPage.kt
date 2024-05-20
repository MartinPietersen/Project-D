package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.GrayLighter
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.NavBarButtons
import com.example.bama.ui.theme.WhiteBroken
import com.example.bama.ui.theme.drawOvalsBehind

@Preview
@Composable
fun LoginPage(navController: NavHostController = rememberNavController()) {

    WhiteBackGround()
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            Box(
                modifier = Modifier.wrapContentSize().background(GrayLighter)
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
                )
                {
                    Row() {
                        Button(
                            onClick = { /*TODO*/ },
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
        content = {
            var padding by remember { mutableStateOf(it) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(it)
                    .background(GrayLighter)
                    .padding(0.dp, 0.dp, 0.dp, 8.dp)
            ){
                Text(
                    text = "Buurtsportcoach",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(0.dp, 16.dp, 16.dp, 0.dp).align(Alignment.Center)
                )
            }
        },
        bottomBar = {
            NavBarButtons(navController)
        }
    )
}