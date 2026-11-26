
package com.asylo.nets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Black = Color(0xFF101010)
private val CardBlack = Color(0xFF1C1C1C)
private val Red = Color(0xFFE53935)
private val Blue = Color(0xFF2979FF)
private val White = Color(0xFFF5F5F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsyloApp()
        }
    }
}

@Composable
fun AsyloApp() {
    var selectedTab by remember { mutableStateOf("Home") }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Black,
            surface = CardBlack,
            primary = Red
        )
    ) {
        Scaffold(
            containerColor = Black,
            bottomBar = {
                NavigationBar(
                    containerColor = CardBlack,
                    contentColor = White
                ) {
                    listOf("Home", "Roster", "Games", "Stats", "Tactics")
                        .forEach { tab ->
                            NavigationBarItem(
                                selected = selectedTab == tab,
                                onClick = { selectedTab = tab },
                                icon = {},
                                label = { Text(tab) }
                            )
                        }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Black)
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp)
            ) {
                Text(
                    "ASYLO NETS",
                    color = White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    "TEAM MANAGEMENT",
                    color = Red,
                    fontSize = 12.sp,
                    letterSpacing = 3.sp
                )

                Spacer(Modifier.height(24.dp))

                when (selectedTab) {
                    "Home" -> Dashboard()
                    "Roster" -> FeatureScreen(
                        "TEAM ROSTER",
                        "Manage players, jersey numbers, positions and player profiles."
                    )
                    "Games" -> FeatureScreen(
                        "SCHEDULE & GAMES",
                        "Organize fixtures, opponents, venues, scores and game results."
                    )
                    "Stats" -> FeatureScreen(
                        "STATISTICS",
                        "Track points, rebounds, assists, steals, blocks and shooting stats."
                    )
                    "Tactics" -> FeatureScreen(
                        "TACTICS & ROTATIONS",
                        "Manage your starting five, rotations, offensive and defensive plans."
                    )
                }
            }
        }
    }
}

@Composable
fun Dashboard() {
    Text(
        "YOUR TEAM. YOUR GAME.",
        color = White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(Modifier.height(16.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        StatCard("WINS", "0", Red, Modifier.weight(1f))
        StatCard("LOSSES", "0", Blue, Modifier.weight(1f))
    }

    Spacer(Modifier.height(12.dp))

    StatCard(
        "NEXT GAME",
        "No game scheduled",
        Red,
        Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(20.dp))

    Text(
        "TEAM HUB",
        color = Red,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(Modifier.height(10.dp))

    FeatureCard("ROSTER", "Your players and their profiles")
    FeatureCard("GAME CENTER", "Upcoming games and past results")
    FeatureCard("TEAM STATS", "Performance and player statistics")
    FeatureCard("TACTICS", "Lineups, plays and rotations")
    FeatureCard("TEAM SETTINGS", "Logo, colors, sponsor and season")
}

@Composable
fun StatCard(
    title: String,
    value: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBlack),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                title,
                color = accent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                value,
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FeatureCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = CardBlack),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(42.dp)
                    .background(Red, RoundedCornerShape(4.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    title,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    description,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun FeatureScreen(title: String, description: String) {
    Spacer(Modifier.height(8.dp))

    Text(
        title,
        color = White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Black
    )

    Spacer(Modifier.height(12.dp))

    FeatureCard(title, description)

    Spacer(Modifier.height(12.dp))

    Text(
        "This section will be built in the next development steps.",
        color = Color.LightGray,
        fontSize = 14.sp
    )
}
