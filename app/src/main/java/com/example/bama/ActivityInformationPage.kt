package com.example.bama

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bama.ui.theme.Green
import coil.compose.rememberAsyncImagePainter
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.GreenDark
import com.example.bama.ui.theme.WhiteBroken

@Composable
@Preview
fun ActivityInformationScreen(navHostController: NavHostController = rememberNavController()) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Overzicht", "Fotos", "Inschrijvingen")
    val onTabSelected = { tab: Int -> selectedTab = tab }

    WhiteBackGround()
    Scaffold(
        containerColor = WhiteBroken,
        topBar = {
            // Back button
            ElevatedButton(
                onClick = { navHostController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = GreenDark.copy(alpha = 0.75f),
                    contentColor = WhiteBroken

                ),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 100.dp,
                    pressedElevation = 0.dp,
                )

            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
                Text("Vorige", style = MaterialTheme.typography.titleMedium)
            }
        },
        content = {
            var padding by remember { mutableStateOf(it) }
            Column(

            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = Green.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(bottomEnd = 55.dp)
                        )
                )
                {
                    // Image
                    LoadImage(
                        url = "https://www.watzijzegt.com/wp-content/uploads/2020/10/gouda-stad-cvchSJpMBk4.jpg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            .clip(shape = RoundedCornerShape(bottomEnd = 55.dp))
                    )
                    // Green hue gradient
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Green.copy(alpha = 0.5f)
                                    )
                                )
                                ,
                                shape = RoundedCornerShape(bottomEnd = 55.dp)
                            )
                    )
                    // Title and date
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "Goudse Stadswandeling",
                            style = MaterialTheme.typography.titleLarge,
                            color = WhiteBroken,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.25.sp
                        )
                        Text(
                            text = "Woensdag 12 juni 2024 - 14:00",
                            style = MaterialTheme.typography.bodyMedium,
                            color = WhiteBroken
                        )
                    }

                }
                // Horizontal row with buttons
                TabRowElement(
                    tabs = tabs,
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayLight.copy(alpha = 0.5f))
                )
                // Description
                // switch case
                when (selectedTab) {
                    0 -> {
                        ActivityDetailsDescription()
                    }
                    1 -> {
                        LoadImage(
                            url = "https://www.watzijzegt.com/wp-content/uploads/2020/10/gouda-stad-cvchSJpMBk4.jpg",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .clip(shape = RoundedCornerShape(bottomEnd = 55.dp))
                        )
                    }
                    2 -> {
                        ActivityDetailsDescription()
                    }
                }
            }
        },
        bottomBar = {
            BottomBarWithRegisterButton()
        }
    )
}

@Composable
fun ActivityDetailsDescription() {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp, 4.dp, 16.dp, 16.dp)
    ) {

        item {
            Text(
                text = "Routebeschrijving",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Text(
                text = "Begin je Goudse Wandeling bij het sfeervolle treinstation van Gouda en wandel richting de binnenstad via de Kleiweg, waar je de lokale winkels kunt bewonderen. Op de Markt, het centrale plein, geniet je van het uitzicht op het indrukwekkende gotische stadhuis en de gezellige terrasjes. Loop vervolgens naar de majestueuze Sint-Janskerk, bekend om haar lange gebrandschilderde ramen. Wandel langs de rustige grachten zoals de Turfmarkt en de Haven, waar de historische huizen prachtig weerspiegelen in het water. Eindig je tocht met een bezoek aan de Waag op de Markt, het historische weeghuis voor kaas, voordat je terugkeert naar het station. Deze route biedt een mooie mix van Gouda's rijke geschiedenis en hedendaagse cultuur.\n\nDeze wandeling neemt je mee door de prachtige stad Gouda. De wandeling is 5 km lang en duurt ongeveer 2 uur. De wandeling is geschikt voor iedereen en is rolstoelvriendelijk. De wandeling is gratis en je hoeft je niet in te schrijven.",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayDark,
                lineHeight = 24.sp
            )
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun BottomBarWithRegisterButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        WhiteBroken.copy(alpha = 0.3f),
                        WhiteBroken.copy(alpha = 0.6f),
                        WhiteBroken.copy(alpha = 0.9f),
                        WhiteBroken
                    ),
                    startY = 0f,
                    endY = 100f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 32.dp, 16.dp, 16.dp)
                .align(Alignment.BottomStart),
        ) {
            Text(text = "Goudse Stadswandeling", style = MaterialTheme.typography.titleMedium)
            Text(text = "Woensdag 12 juni 2024\n14:00 t/m 16:00", style = MaterialTheme.typography.bodyMedium)
        }
        ElevatedButton(
            onClick = {},
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .align(Alignment.CenterEnd),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = GreenDark,
                contentColor = WhiteBroken
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 0.dp,
            )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Inschrijven")
            Text("Loop mee!", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun TabRowElement(tabs: List<String>, selectedTab: Int, onTabSelected: (Int) -> Unit = {}) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(WhiteBroken)
    ) {
        tabs.forEachIndexed { index, title ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource(),
                        onClick = { onTabSelected(index) })
                    .padding(16.dp),
            ) {
                Text(
                    text = title,
                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                )
                if (selectedTab == index) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(40.dp)
                            .background(Green, shape = RoundedCornerShape(1.dp))
                    )
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun LoadImage(url: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun ActivityTopBar() {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
    }
}
