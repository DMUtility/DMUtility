package com.wyq0918dev.dmutility.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AllInbox
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.wyq0918dev.dmutility.R
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

@Serializable
data object ToolsDestination

@Serializable
data object DiscoverDestination

@Serializable
data object DashboardDestination

@Serializable
data object PlatformDestination

@Serializable
data object SettingsDestination

data class AppDestination<T>(
    val label: Int,
    val route: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val appDestination = arrayListOf(
    AppDestination(
        label = R.string.destination_home,
        route = HomeDestination,
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
    AppDestination(
        label = R.string.destination_tools,
        route = ToolsDestination,
        icon = Icons.Outlined.AllInbox,
        selectedIcon = Icons.Filled.AllInbox,
    ),
    AppDestination(
        label = R.string.destination_discover,
        route = DiscoverDestination,
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
    ),
    AppDestination(
        label = R.string.destination_settings,
        route = SettingsDestination,
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
    ),
)

val discoverDestination: ArrayList<AppDestination<out Any>> = arrayListOf(
    AppDestination(
        label = R.string.destination_dashboard,
        route = DashboardDestination,
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
    ),
    AppDestination(
        label = R.string.destination_platform,
        route = PlatformDestination,
        icon = Icons.Outlined.Category,
        selectedIcon = Icons.Filled.Category,
    ),
)