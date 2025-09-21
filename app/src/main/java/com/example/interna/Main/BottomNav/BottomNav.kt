package com.example.interna.Main.BottomNav

import BottomNavItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.interna.Main.Companies.CompaniesScreen
import com.example.interna.Main.Home.HomeScreen
import com.example.interna.Main.Requirements.RequirementsScreen
import com.example.interna.Main.Settings.SettingsScreen
import com.example.interna.Main.WeeklyReport.WeeklyReportScreen

sealed class NavItem(val route: String, val label: String, val lottieIcon: String?) {
    object Home : NavItem("home", "Home", "home")
    object WeeklyReport : NavItem("weeklyReport", "Weekly", "article_icon")
    object Companies : NavItem("companies", "Company", "newspaper")
    object Requirements : NavItem("requirements", "Files", "inbox")
    object Settings : NavItem("settings", "Settings", "settings")
}

@Composable
fun BottomNavScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()

    val items = listOf(
        NavItem.Home,
        NavItem.WeeklyReport,
        NavItem.Companies,
        NavItem.Requirements,
        NavItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define which routes should show the bottom nav
    val bottomNavRoutes = items.map { it.route }

    // Show bottom nav if current route is in bottomNavRoutes OR if it's null (app start)
    val showBottomNav = currentRoute == null || currentRoute in bottomNavRoutes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomNav,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut(),
                modifier = Modifier.height(60.dp)
            ) {
                BottomNavBar(navController = navController, items = items, isDarkTheme = isDarkTheme)
            }
        }
    ) { innerPadding ->
        // Animate the padding change
        val bottomPadding by animateDpAsState(
            targetValue = if (showBottomNav) 60.dp else 0.dp,
            animationSpec = tween(durationMillis = 100)
        )

        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding),  // Use the animated padding here
            enterTransition = { fadeIn(animationSpec = tween(100)) },
            exitTransition = { fadeOut(animationSpec = tween(100)) },
        ) {
            //main bottom nav routes
            composable("home") { backStackEntry -> HomeScreen(navController = navController) }
            composable("weeklyReport") { WeeklyReportScreen(navController = navController) }
            composable("requirements") { RequirementsScreen(navController = navController) }
            composable("companies") { CompaniesScreen(navController = navController) }
            composable("settings") { SettingsScreen(navController = navController) }

        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController,
    items: List<NavItem>,
    isDarkTheme: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // List of bottom nav routes
    val bottomNavRoutes = items.map { it.route }

    // Show bottom nav if current route is in bottomNavRoutes OR if it's null (app start)
    val showBottomNav = currentRoute == null || currentRoute in bottomNavRoutes

    AnimatedVisibility(
        visible = showBottomNav,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
        modifier = Modifier.height(60.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp)
                        .alpha(0.6f)
                        .background(MaterialTheme.colorScheme.onSurface)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items.forEach { item ->
                        BottomNavItem(
                            label = item.label,
                            animationFile = item.lottieIcon,
                            isSelected = currentRoute == item.route,
                            isDarkTheme = isDarkTheme
                        ) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            }
        }
    }
}

