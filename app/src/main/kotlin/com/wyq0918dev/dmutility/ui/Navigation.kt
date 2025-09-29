package com.wyq0918dev.dmutility.ui

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AllInbox
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardCommandKey
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

@Serializable
data object ToolsDestination

@Serializable
data object DiscoverDestination

@Serializable
data object DashboardPage

@Serializable
data object EcosedKitPage

@Serializable
data object SettingsDestination

data class AppDestination<T>(
    val label: String,
    val route: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val appDestination = arrayListOf(
    AppDestination(
        label = "Home",
        route = HomeDestination,
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
    AppDestination(
        label = "Tools",
        route = ToolsDestination,
        icon = Icons.Outlined.AllInbox,
        selectedIcon = Icons.Filled.AllInbox,
    ),
    AppDestination(
        label = "Discover",
        route = DiscoverDestination,
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
    ),
    AppDestination(
        label = "Settings",
        route = SettingsDestination,
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
    ),
)

val discoverDestination: ArrayList<AppDestination<out Any>> = arrayListOf(
    AppDestination(
        label = "Dashboard",
        route = DashboardPage,
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
    ),
    AppDestination(
        label = "EcosedKit",
        route = EcosedKitPage,
        icon = Icons.Outlined.KeyboardCommandKey,
        selectedIcon = Icons.Filled.KeyboardCommandKey,
    ),
)

fun <T : Any> PagerState.isCurrentDestination(route: T): Boolean {
    return this@isCurrentDestination.currentPage == discoverDestination.indexOfFirst { index ->
        return@indexOfFirst index.route == route
    }
}

suspend fun <T : Any> PagerState.animateToRoute(route: T) {
    return this@animateToRoute.animateScrollToPage(
        page = discoverDestination.indexOfFirst { index ->
            return@indexOfFirst index.route == route
        },
    )
}
