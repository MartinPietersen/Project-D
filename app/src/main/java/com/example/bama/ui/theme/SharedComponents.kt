package com.example.bama.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NavBarButtons()
{
    Row(modifier = Modifier.fillMaxWidth().background(WhiteBroken)) {
        Button(
            onClick = {},
            modifier = Modifier.weight(0.20f),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBroken,
                contentColor = Color.DarkGray
            ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                Text(
                    text = "Home",
                    softWrap = false,
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(0.30f),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBroken,
                contentColor = Color.DarkGray
            ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Buurtsportcoach")
                Text(
                    text = "Buurtsportcoach",
                    softWrap = false
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(0.25f),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBroken,
                contentColor = Color.DarkGray
            ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Activiteiten")
                Text(
                    text = "Activiteiten",
                    softWrap = false
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(0.25f),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBroken,
                contentColor = Color.DarkGray
            ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Instellingen")
                Text(
                    text = "Instellingen",
                    softWrap = false
                )
            }
        }
    }
}