package com.example.bama

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bama.ui.theme.Gray
import com.example.bama.ui.theme.GrayDark
import com.example.bama.ui.theme.Green
import com.example.bama.ui.theme.NavBarButtons
import com.example.bama.ui.theme.WhiteBroken


@Composable
@Preview
fun ActivitiesScreen() {
    var selectedButtonIndex by remember { mutableStateOf(0) }

    val onValueChange: (Int) -> Unit = { index ->
        selectedButtonIndex = index
    }
    WhiteBackGround()
    Scaffold(
        topBar = {
        },
        content = {
            var padding by remember { mutableStateOf(it) }
            Column {
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
                    Column {
                        TopBar(Modifier)
                        SearchBar(Modifier)
                    }
                }

                Column {
                    ActivitiesStatusBar(selectedButtonIndex, onValueChange)
                    Spacer(modifier = Modifier.height(8.dp))
                    UpcomingActivities()
                    ActivityGrid(Modifier.padding(it))
                }
            }
        },
        bottomBar = {
            NavBarButtons()
        }
    )
}

private fun Modifier.drawOvalsBehind(): Modifier {
    return drawBehind {
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

@Composable
fun UpcomingActivities() {
    Column(
        modifier = Modifier.padding()
    ) {
        Row(
            modifier = Modifier.padding(12.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Aankomende activiteiten", style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = WhiteBroken,
                    containerColor = GrayDark
                ),
                contentPadding = PaddingValues(8.dp, 0.dp),
                modifier = Modifier.wrapContentHeight()) {
                Text(text = "Bekijk alles")
            }
        }
        LazyRow() {
            item {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Activiteit 1")
                }

            }
            
        }
    }
}

@Composable
fun ActivityCard() {

}

@Preview
@Composable
fun WhiteBackGround() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = WhiteBroken
            )
    )

}

@Composable
fun ActivitiesStatusBar(selectedButtonIndex: Int, onValueChange: (Int) -> Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActivityStatusButton(
            icon = Icons.Default.DateRange,
            description = "Overzicht",
            color = if (selectedButtonIndex == 0) Green else Gray,
            modifier = Modifier.weight(0.33f),
            onValueChange = { onValueChange(0) },
        )
        ActivityStatusButton(
            icon = Icons.Default.DateRange,
            description = "Overzicht",
            modifier = Modifier.weight(0.33f),
            color = if (selectedButtonIndex == 1) Green else Gray,
            onValueChange = { onValueChange(1) },
        )
        ActivityStatusButton(
            icon = Icons.Default.DateRange,
            description = "Overzicht",
            modifier = Modifier.weight(0.33f),
            color = if (selectedButtonIndex == 2) Green else Gray,
            onValueChange = { onValueChange(2) },
        )
    }
}

@Composable
fun ActivityStatusButton(
    icon: ImageVector,
    description: String,
    modifier: Modifier,
    color: Color,
    onValueChange: () -> Unit,
) {
    //
    Button(
        onClick = onValueChange,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .background(WhiteBroken)
                .padding(4.dp)) {
                Icon(
                    imageVector = icon, contentDescription = description,
                    modifier = Modifier,
                    tint = color
                )
            }
            Text(
                text = description,
            )
        }
    }
}

@Composable
fun ActivitiesTopBox(modifier: Modifier) {
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(
//                color = Green,
//                shape = RoundedCornerShape(bottomEnd = 55.dp)
//            )
//    ) {
    Column {
        OverlayedOvals()
        TopBar(Modifier)
        SearchBar(Modifier)
    }
//    }
}
// Green background with rounded bottom right corner

@Composable
fun TopBar(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = "Activiteiten",
            color = Color.White,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 1.sp
        )
        Text(
            text = "Welkom op de activiteitenpagina! Hier vindt u een overzicht van alle activiteiten waarvoor u zich nog niet heeft ingeschreven, of juist wel.",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier) {
    var search by remember { mutableStateOf("") }
    OutlinedTextField(
        shape = RoundedCornerShape(8.dp, 8.dp, 55.dp, 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp),
        value = search,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        onValueChange = { search = it },
        placeholder = {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = GrayDark
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Waar bent u naar op zoek?", color = GrayDark
                )
            }
        })
}

data class Activity(val name: String)

@Composable
fun ActivityGrid(weight: Modifier) {
    val activities = listOf<Activity>(
        Activity("Walking"),
        Activity("Cycling"),
        Activity("Running"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing"),
        Activity("Cycling"),
        Activity("Running"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing"),
        Activity("Cycling"),
        Activity("Running"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing"),
        Activity("Swimming"),
        Activity("Jumping"),
        Activity("Dancing")
    )
    // 7 Green tints for the buttons
    val colors = listOf(
        Color(0xFF116F47),
        Color(0xFF138A5A),
        Color(0xFF14A86A),
        Color(0xFF16C17A),
        Color(0xFF18E08A),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(activities.size) { activity ->
            ActivityButton(activity = activities[activity], color = colors[activity % colors.size])
        }
    }
}