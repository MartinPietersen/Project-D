package com.example.bama

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bama.ui.theme.Gray
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.GrayLight
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.GreenDark
import com.example.bama.ui.theme.GreenLight
import com.example.bama.ui.theme.GreenLight2
import com.example.bama.ui.theme.WhiteBroken

@Composable
@Preview
fun HomePage(navHostController: NavHostController = rememberNavController()) {
    // get the screen height
    // get the screen width
    val configuration = LocalConfiguration.current
    val heightDP = configuration.screenHeightDp
    val widthDP = configuration.screenWidthDp

    Scaffold(
        containerColor = WhiteBroken,
        topBar = {

        },

        content = {
            val padding = it
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                GreenRoundedRectangle(heightDP)
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .consumeWindowInsets(it)


                ) {
                    Greeting("John")
                    Spacer(modifier = Modifier.height(16.dp))
                    LayeredCirclesWithPoints(widthDP, Modifier.align(Alignment.CenterHorizontally))

                    LazyColumn {
                        item {
                            CardWithStatButtons()
                        }
                    }

                }
            }
        },

        bottomBar = {}
    )
}

@Composable
fun CardWithStatButtons() {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = "Uw Statistieken",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonWithActivityStats(
                icon = Icons.Filled.DateRange,
                text = "Activiteiten",
                number = "2",
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                color = Green
            )
            ButtonWithActivityStats(
                icon = Icons.Default.Person,
                text = "Stappen",
                number = "50",
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                color = GreenLight
            )

        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonWithActivityStats(
                icon = Icons.Filled.DateRange,
                text = "Activiteiten",
                number = "2",
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                color = GreenDark
            )
            ButtonWithActivityStats(
                icon = Icons.Default.Person,
                text = "Stappen",
                number = "50",
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                color = Green


            )

        }
    }
}

@Composable
fun ButtonWithActivityStats(
    icon: ImageVector,
    text: String,
    number: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(0.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = WhiteBroken,
//                modifier = Modifier.align(Alignment.Top)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    color = WhiteBroken,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = number.toString(),
                    color = WhiteBroken,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Welkom $name!", style = MaterialTheme.typography.titleLarge, color = WhiteBroken)
    Text(
        text = "Hier zie je een overzicht van je gegevens",
        style = MaterialTheme.typography.bodyMedium,
        color = WhiteBroken
    )
}

@Composable
fun LayeredCirclesWithPoints(widthDP: Int, modifier: Modifier) {
    var bigBox = (widthDP * 0.5).dp
    var middleBox = (bigBox.value * 0.85).dp
    var smallBox = (middleBox.value * 0.80).dp

    Box(
        modifier = modifier
            .width(bigBox)
            .height(bigBox)
            .background(color = WhiteBroken.copy(alpha = 0.7f), shape = CircleShape)
    )
    {
        Box(
            modifier = Modifier
                .width(middleBox)
                .height(middleBox)
                .background(color = GrayLight.copy(alpha = 0.7f), shape = CircleShape)
                .align(Alignment.Center)
        )
        {
            Box(
                modifier = Modifier
                    .width(smallBox)
                    .height(smallBox)
                    .align(Alignment.Center)
                    .background(color = WhiteBroken, shape = CircleShape)
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                )
                {
                    Text(
                        text = "Uw punten",
                        color = Green,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "150 pt",
                        color = Green,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun GreenRoundedRectangle(heightDP: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((heightDP * 0.5).dp)
            .background(Green, shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 0.dp))
            .drawOvalsBehind()
    )

}
