package com.alokparna.portfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.data.Achievement
import com.alokparna.portfolio.data.Portfolio
import com.alokparna.portfolio.data.Publication

@Composable
fun AchievementsScreen(portfolio: Portfolio, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            Text(
                text = "Achievements & Recognition",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier.height(400.dp), // Adjust height as needed
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(portfolio.achievements.size) { index ->
                    AchievementCard(portfolio.achievements[index])
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Research & Publications",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(portfolio.publications.size) { index ->
            PublicationCard(publication = portfolio.publications[index])
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recognized for technical excellence and innovation across multiple domains including blockchain, healthcare technology, and AI research.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = achievement.color.map { Color(android.graphics.Color.parseColor(it)) }
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getIconForAchievement(achievement.icon),
                    contentDescription = achievement.title,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = achievement.title, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = achievement.event,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = achievement.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun PublicationCard(publication: Publication) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { uriHandler.openUri(publication.url) }),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getIconForPublication(publication.icon),
                    contentDescription = publication.title,
                    tint = Color.White
                )
            }
            Column(modifier = Modifier.padding(start = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = publication.title, style = MaterialTheme.typography.titleLarge)
                Text(text = publication.type, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                Text(text = publication.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

fun getIconForAchievement(iconName: String): ImageVector {
    return when (iconName) {
        "Trophy" -> Icons.Default.EmojiEvents
        "Award" -> Icons.Default.Star
        "Star" -> Icons.Default.Star
        else -> Icons.Default.Star
    }
}

fun getIconForPublication(iconName: String): ImageVector {
    return when (iconName) {
        "FileText" -> Icons.Default.ReceiptLong
        "BookOpen" -> Icons.Default.Book
        else -> Icons.Default.ReceiptLong
    }
}
