package com.alokparna.portfolio.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, val icon: ImageVector) {
    object About : Screen("about", Icons.Default.Person)
    object Education : Screen("education", Icons.Default.School)
    object Experience : Screen("experience", Icons.Default.Work)
    object Projects : Screen("projects", Icons.Default.Code)
    object Skills : Screen("skills", Icons.Default.Star)
    object Achievements : Screen("achievements", Icons.Default.EmojiEvents)
    object Contact : Screen("contact", Icons.Default.Email)
}

val items = listOf(
    Screen.About,
    Screen.Education,
    Screen.Experience,
    Screen.Projects,
    Screen.Skills,
    Screen.Achievements,
    Screen.Contact
)

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = viewModel()) {
    val navController = rememberNavController()
    val portfolio by viewModel.portfolio.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.About.route, Modifier.padding(innerPadding)) {
            composable(Screen.About.route) { AboutScreen(portfolio) }
            composable(Screen.Education.route) { EducationScreen(portfolio) }
            composable(Screen.Experience.route) { ExperienceScreen(portfolio) }
            composable(Screen.Projects.route) { ProjectsScreen(portfolio) }
            composable(Screen.Skills.route) { SkillsScreen(portfolio) }
            composable(Screen.Achievements.route) { AchievementsScreen(portfolio) }
            composable(Screen.Contact.route) { ContactScreen(portfolio) }
        }
    }
}