package com.alokparna.portfolio.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alokparna.portfolio.data.Portfolio
import kotlinx.coroutines.launch

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Hero : Screen("hero", Icons.Default.Person, "Hero")
    object About : Screen("about", Icons.Default.Person, "About Me")
    object Education : Screen("education", Icons.Default.School, "Education")
    object Experience : Screen("experience", Icons.Default.Work, "Experience")
    object Projects : Screen("projects", Icons.Default.Code, "Projects")
    object Skills : Screen("skills", Icons.Default.Star, "Skills")
    object Achievements : Screen("achievements", Icons.Default.EmojiEvents, "Achievements & Recognition")
    object Publications : Screen("publications", Icons.AutoMirrored.Filled.Article, "Research & Publications")
    object Contact : Screen("contact", Icons.Default.Email, "Contact Me")
}

val drawerItems = listOf(
    Screen.About,
    Screen.Education,
    Screen.Experience,
    Screen.Projects,
    Screen.Skills,
    Screen.Achievements,
    Screen.Publications,
    Screen.Contact
)

@Composable
fun PortfolioApp(viewModel: PortfolioViewModel = viewModel()) {
    val portfolio by viewModel.portfolio.collectAsState()
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Hero.route) {
        composable(Screen.Hero.route) {
            HeroScreen(portfolio, onNavigateToPortfolio = { navController.navigate("main") })
        }
        composable("main") {
            MainPortfolioScreen(portfolio)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPortfolioScreen(portfolio: Portfolio) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                drawerItems.forEach { screen ->
                    NavigationDrawerItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Alokparna Mitra") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = Screen.About.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.About.route) { AboutScreen(portfolio) }
                composable(Screen.Education.route) { EducationScreen(portfolio) }
                composable(Screen.Experience.route) { ExperienceScreen(portfolio) }
                composable(Screen.Projects.route) { ProjectsScreen(portfolio) }
                composable(Screen.Skills.route) { SkillsScreen(portfolio) }
                composable(Screen.Achievements.route) { AchievementsScreen(portfolio) }
                composable(Screen.Publications.route) { PublicationsScreen(portfolio) }
                composable(Screen.Contact.route) { ContactScreen(portfolio) }
            }
        }
    }
}
