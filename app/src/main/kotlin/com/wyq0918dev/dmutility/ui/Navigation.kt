package com.wyq0918dev.dmutility.ui

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.KeyboardCommandKey
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
data object DashboardPage

@Serializable
data object EcosedKitPage

data class AppDestination<T>(
    val label: String,
    val route: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val appDestination: ArrayList<AppDestination<out Any>> = arrayListOf(
    AppDestination(
        label = "",
        route = DashboardPage,
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
    ),
    AppDestination(
        label = "",
        route = EcosedKitPage,
        icon = Icons.Outlined.KeyboardCommandKey,
        selectedIcon = Icons.Filled.KeyboardCommandKey,
    ),
)

fun <T : Any> PagerState.isCurrentDestination(page: T): Boolean {
    return this@isCurrentDestination.currentPage == appDestination.indexOfFirst { index ->
        return@indexOfFirst index.route == page
    }
}

suspend fun <T : Any> PagerState.animateToRoute(route: T) {
    return this@animateToRoute.animateScrollToPage(
        page = appDestination.indexOfFirst { index ->
            return@indexOfFirst index.route == route
        },
    )
}
