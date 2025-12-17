package com.alokparna.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alokparna.portfolio.ui.PortfolioApp
import com.alokparna.portfolio.ui.theme.AlokparnaPortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlokparnaPortfolioTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PortfolioApp()
                }
            }
        }
    }
}