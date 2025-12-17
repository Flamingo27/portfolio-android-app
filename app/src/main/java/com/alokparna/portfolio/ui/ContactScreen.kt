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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alokparna.portfolio.data.Portfolio

@Composable
fun ContactScreen(portfolio: Portfolio, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    LazyColumn(modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Text(
                text = "Get In Touch",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Let's discuss your next project or opportunity",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            ContactInfoSection(portfolio)
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            SendMessageForm(
                name = name, onNameChange = { name = it },
                email = email, onEmailChange = { email = it },
                message = message, onMessageChange = { message = it }
            )
        }
    }
}

@Composable
fun ContactInfoSection(portfolio: Portfolio) {
    val uriHandler = LocalUriHandler.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Contact Information", style = MaterialTheme.typography.headlineMedium)
        ContactInfoItem(icon = Icons.Default.Email, label = "Email", value = portfolio.contact.email, onClick = { uriHandler.openUri("mailto:${portfolio.contact.email}") })
        ContactInfoItem(icon = Icons.Default.Phone, label = "Phone", value = portfolio.contact.phone, onClick = { uriHandler.openUri("tel:${portfolio.contact.phone}") })
        ContactInfoItem(icon = Icons.Default.LocationOn, label = "Location", value = portfolio.contact.location, onClick = { uriHandler.openUri("geo:0,0?q=${portfolio.contact.location}") })

        Spacer(modifier = Modifier.height(16.dp))
        Text("Connect Online", style = MaterialTheme.typography.headlineMedium)
        ContactInfoItem(icon = Icons.Default.Link, label = "GitHub", value = portfolio.contact.github, onClick = { uriHandler.openUri(portfolio.contact.github) })
        ContactInfoItem(icon = Icons.Default.Link, label = "LinkedIn", value = portfolio.contact.linkedin, onClick = { uriHandler.openUri(portfolio.contact.linkedin) })
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector, label: String, value: String, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = label, tint = Color.White)
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(label, style = MaterialTheme.typography.labelMedium)
                Text(value, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun SendMessageForm(name: String, onNameChange: (String) -> Unit, email: String, onEmailChange: (String) -> Unit, message: String, onMessageChange: (String) -> Unit) {
    Card(elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Send a Message", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = name, onValueChange = onNameChange, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = onEmailChange, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = message, onValueChange = onMessageChange, label = { Text("Message") }, modifier = Modifier.fillMaxWidth(), maxLines = 5)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Implement send message */ }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Send, contentDescription = "Send Message", modifier = Modifier.padding(end = 8.dp))
                Text("Send Message")
            }
        }
    }
}