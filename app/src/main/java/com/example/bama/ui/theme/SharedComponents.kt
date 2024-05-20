package com.example.bama.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun NavBarButtons(navHostController: NavHostController) {
    Column {
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(GrayLight)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhiteBroken)
        ) {
            Button(
                onClick = {
                    navigateTo(navHostController, "HomePage")
                },
                modifier = Modifier.weight(0.20f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhiteBroken,
                    contentColor = Color.DarkGray
                ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Buurtsportcoach")
                    Text(
                        text = "Chat",
                        softWrap = false
                    )
                }
            }
            Button(
                onClick = {
                    navigateTo(navHostController, "ActivitiesPage")
                },
                modifier = Modifier.weight(0.25f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhiteBroken,
                    contentColor = Color.DarkGray
                ), contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Instellingen")
                    Text(
                        text = "Instellingen",
                        softWrap = false
                    )
                }
            }
        }
    }
}

internal fun Modifier.drawOvalsBehind(color: Color = WhiteBroken): Modifier {
    return drawBehind {
        drawOval(
            color = color.copy(alpha = 0.25f), topLeft = Offset(
                x = size.width - 80.dp.toPx(), y = -50.dp.toPx()
            ), size = Size(
                width = 180.dp.toPx(), height = 180.dp.toPx()
            )
        )

        // Small circle above the big circle
        drawOval(
            color = color.copy(alpha = 0.25f), topLeft = Offset(
                x = size.width - 150.dp.toPx(), y = -90.dp.toPx()
            ), size = Size(
                width = 130.dp.toPx(), height = 130.dp.toPx()
            )
        )

        // Small circle below the big circle to the right
        drawOval(
            color = color.copy(alpha = 0.25f), topLeft = Offset(
                x = size.width - 40.dp.toPx(), y = 250.dp.toPx()
            ), size = Size(
                width = 130.dp.toPx(), height = 130.dp.toPx()
            )
        )

        //Big circle left side of the screen
        drawOval(
            color = color.copy(alpha = 0.25f), topLeft = Offset(
                x = -110.dp.toPx(), y = 110.dp.toPx()
            ), size = Size(
                width = 220.dp.toPx(), height = 220.dp.toPx()
            )
        )
    }
}

fun navigateTo(navController: NavHostController, route: String) {

    if (navController.previousBackStackEntry?.destination?.route == route){
        navController.popBackStack()
    }
    else if (navController.currentDestination?.route != route) {
        navController.navigate(route)
    }
}