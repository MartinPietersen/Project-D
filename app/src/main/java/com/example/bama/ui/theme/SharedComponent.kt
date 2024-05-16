package com.example.bama.ui.theme

import android.app.Activity
import android.os.Build
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.bama.ActivityStatusButton


@Composable
fun NavBarButtons()
{/*hi*/
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