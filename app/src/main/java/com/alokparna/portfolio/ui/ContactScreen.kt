package com.alokparna.portfolio.ui

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alokparna.portfolio.R
import com.alokparna.portfolio.data.Portfolio

@Composable
fun ContactScreen(portfolio: Portfolio, modifier: Modifier = Modifier, viewModel: PortfolioViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val contactUiState by viewModel.contactUiState.collectAsState()

    LaunchedEffect(contactUiState) {
        if (contactUiState is ContactUiState.Success) {
            name = ""
            email = ""
            message = ""
        }
    }

    LazyColumn(modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Text(
                text = "Get In Touch",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Let's discuss your next project or opportunity",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
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
                message = message, onMessageChange = { message = it },
                uiState = contactUiState,
                onSendMessage = { viewModel.sendContactMessage(name, email, message) },
                onResetState = { viewModel.resetContactState() }
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
        ContactInfoItem(painter = painterResource(id = R.drawable.github_logo), label = "GitHub", value = "Flamingo27", onClick = { uriHandler.openUri(portfolio.contact.github) })
        ContactInfoItem(painter = painterResource(id = R.drawable.linkedin_logo), label = "LinkedIn", value = "alokparna-mitra", onClick = { uriHandler.openUri(portfolio.contact.linkedin) })
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector? = null, painter: androidx.compose.ui.graphics.painter.Painter? = null, label: String, value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(16.dp)).clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                if (icon != null) {
                    Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.onPrimary)
                } else if (painter != null) {
                    Icon(painter = painter, contentDescription = label, tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(label, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                Text(value, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun SendMessageForm(
    name: String, onNameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    message: String, onMessageChange: (String) -> Unit,
    uiState: ContactUiState,
    onSendMessage: () -> Unit,
    onResetState: () -> Unit
) {
    var isEmailValid by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Send a Message", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(value = name, onValueChange = onNameChange, label = { Text("Name") }, modifier = Modifier.fillMaxWidth(), enabled = uiState !is ContactUiState.Loading)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email, 
                onValueChange = { 
                    onEmailChange(it)
                    isEmailValid = isEmailValid(it)
                }, 
                label = { Text("Email") }, 
                modifier = Modifier.fillMaxWidth(), 
                enabled = uiState !is ContactUiState.Loading,
                isError = !isEmailValid
            )
            if (!isEmailValid) {
                Text("Invalid email address", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = message, onValueChange = onMessageChange, label = { Text("Message") }, modifier = Modifier.fillMaxWidth(), maxLines = 5, enabled = uiState !is ContactUiState.Loading)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onSendMessage,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                enabled = uiState !is ContactUiState.Loading && name.isNotEmpty() && isEmailValid && email.isNotEmpty() && message.isNotEmpty()
            ) {
                if (uiState is ContactUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send Message", modifier = Modifier.padding(end = 8.dp))
                    Text("Send Message", style = MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (uiState) {
                is ContactUiState.Success -> {
                    Text("✅ Message sent! I'll get back to you soon.", color = Color.Green, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    LaunchedEffect(Unit) { kotlinx.coroutines.delay(3000); onResetState() }
                }
                is ContactUiState.Error -> {
                    Text("❌ Something went wrong. Please try again later.", color = Color.Red, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    LaunchedEffect(Unit) { kotlinx.coroutines.delay(3000); onResetState() }
                }
                else -> {}
            }
        }
    }
}