package com.alokparna.portfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.R
import com.alokparna.portfolio.data.Portfolio
import com.alokparna.portfolio.data.Project
import com.alokparna.portfolio.data.ProjectLink

@Composable
fun ProjectsScreen(portfolio: Portfolio, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            Text(
                text = "Featured Projects",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Innovative solutions across blockchain, AI, and IoT",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(portfolio.projects.size) { index ->
            ProjectCard(portfolio.projects[index])
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                val imageId = when (project.image) {
                    "project_unicred" -> R.drawable.project_unicred
                    "project_irrigation" -> R.drawable.project_irrigation
                    "project_stocks" -> R.drawable.project_stocks
                    "project_assistive" -> R.drawable.project_assistive
                    else -> R.drawable.hero_background
                }
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = project.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = project.gradient.map { Color(android.graphics.Color.parseColor(it)) }
                            )
                        )
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = project.title, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = project.subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
                if (project.achievement.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Achievement",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = project.achievement,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 8.dp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    project.tech.forEach {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.tertiaryContainer,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                if (project.links.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        project.links.forEach { link ->
                            LinkButton(link)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LinkButton(link: ProjectLink) {
    IconButton(
        onClick = { /* TODO */ },
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val icon = when (link.type) {
            "demo" -> Icons.Default.ArrowForward
            "drive" -> Icons.Default.DateRange
            else -> Icons.Default.Info
        }
        Icon(icon, contentDescription = link.label, tint = MaterialTheme.colorScheme.onPrimary)
    }
}