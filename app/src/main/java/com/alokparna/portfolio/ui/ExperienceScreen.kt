package com.alokparna.portfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.data.Experience
import com.alokparna.portfolio.data.Portfolio

@Composable
fun ExperienceScreen(
    portfolio: Portfolio,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        item {
            Text(
                text = "Experience",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        items(portfolio.experience.size) { index ->
            ExperienceCard(portfolio.experience[index])
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ExperienceCard(experience: Experience) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = experience.role,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            Icons.Default.Business,
                            contentDescription = "Company",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = experience.company,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        Icons.Default.CalendarToday,
                        contentDescription = "Period",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = experience.duration,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = experience.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Highlights
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                experience.highlights.forEach { highlight ->
                    Row(verticalAlignment = Alignment.Top) {

                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        val annotatedText = buildAnnotatedString {
                            val isDemo = highlight.contains("Live Demo")
                            val isReport = highlight.contains("TechMag Report")

                            when {
                                isDemo && experience.links.containsKey("demo") -> {
                                    val idx = highlight.indexOf("Live Demo")
                                    append(highlight.take(idx))

                                    withLink(
                                        LinkAnnotation.Url(
                                            url = experience.links["demo"]!!,
                                            styles = TextLinkStyles(
                                                style = SpanStyle(
                                                    color = MaterialTheme.colorScheme.primary,
                                                    textDecoration = TextDecoration.Underline
                                                )
                                            )
                                        )
                                    ) {
                                        append("Live Demo")
                                    }

                                    append(highlight.drop(idx + "Live Demo".length))
                                }

                                isReport && experience.links.containsKey("report") -> {
                                    val idx = highlight.indexOf("TechMag Report")
                                    append(highlight.take(idx))

                                    withLink(
                                        LinkAnnotation.Url(
                                            url = experience.links["report"]!!,
                                            styles = TextLinkStyles(
                                                style = SpanStyle(
                                                    color = MaterialTheme.colorScheme.primary,
                                                    textDecoration = TextDecoration.Underline
                                                )
                                            )
                                        )
                                    ) {
                                        append("TechMag Report")
                                    }

                                    append(highlight.drop(idx + "TechMag Report".length))
                                }

                                else -> append(highlight)
                            }
                        }

                        Text(
                            text = annotatedText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }
        }
    }
}
