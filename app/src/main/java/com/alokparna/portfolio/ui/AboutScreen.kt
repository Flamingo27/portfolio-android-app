package com.alokparna.portfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.R
import com.alokparna.portfolio.data.Portfolio

@Composable
fun AboutScreen(portfolio: Portfolio, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "About Me",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.alokparna_speaking),
                contentDescription = "Alokparna Mitra speaking at an event",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "I'm a passionate ${portfolio.title} currently pursuing ${portfolio.education.first().degree} at the ${portfolio.education.first().institution} with a stellar ${portfolio.education.first().score}. My journey in technology is driven by a desire to create innovative solutions that make a real difference.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "As ${portfolio.experience.first().role} of ${portfolio.experience.first().company}, I'm developing an AI-integrated healthcare platform that combines cutting-edge technology with practical healthcare solutions. My expertise spans from web development to blockchain, IoT, and hardware prototyping.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InfoCard(
                    icon = Icons.Default.School,
                    title = portfolio.education.first().score,
                    subtitle = "Academic Excellence"
                )
                InfoCard(
                    icon = Icons.Default.Star,
                    title = "${portfolio.achievements.size}+ Awards",
                    subtitle = "Competition Winner"
                )
                InfoCard(
                    icon = Icons.Default.Book,
                    title = "Author",
                    subtitle = "Published Writer"
                )
            }
        }
    }
}

@Composable
fun InfoCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier.size(110.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
        }
    }
}