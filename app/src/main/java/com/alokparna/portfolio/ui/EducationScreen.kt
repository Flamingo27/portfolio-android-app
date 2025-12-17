package com.alokparna.portfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.R
import com.alokparna.portfolio.data.Education
import com.alokparna.portfolio.data.Portfolio

@Composable
fun EducationScreen(portfolio: Portfolio, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            Text(
                text = "Education",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(portfolio.education.size) { index ->
            EducationCard(portfolio.education[index])
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                SummaryCard(title = "9.1", subtitle = "Current GPA")
                SummaryCard(title = "98.6%", subtitle = "ICSE Score")
                SummaryCard(title = "CS", subtitle = "Specialization")
            }
        }
    }
}

@Composable
fun EducationCard(education: Education) {
    val highlightColor = if (education.highlight) MaterialTheme.colorScheme.primary else Color.Gray
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (education.highlight) 2.dp else 1.dp,
                color = if (education.highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = "${education.institution} logo",
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(highlightColor)
                        .padding(12.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = education.degree, style = MaterialTheme.typography.titleLarge)
                    Text(text = education.institution, style = MaterialTheme.typography.titleMedium, color = highlightColor)
                    Text(text = education.location, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = "Period", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                    Text(text = education.duration, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                }
                Text(
                    text = education.score,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (education.highlight) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            color = if (education.highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun SummaryCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier.size(110.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
            Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
        }
    }
}