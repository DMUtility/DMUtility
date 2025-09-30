package com.wyq0918dev.dmutility.ui.utils

import androidx.compose.foundation.pager.PagerState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.wyq0918dev.dmutility.ui.navigation.discoverDestination

fun <T : Any> NavBackStackEntry?.isCurrentNavDestination(route: T): Boolean {
    return this@isCurrentNavDestination?.destination?.hierarchy?.any {
        return@any it.hasRoute(route = route::class)
    } == true
}

fun <T : Any> NavHostController?.navigateToNavRoute(route: T) {
    return this@navigateToNavRoute?.navigate(route = route) {
        popUpTo(
            id = this@navigateToNavRoute.graph.findStartDestination().id,
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } ?: Unit
}

fun <T : Any> PagerState?.isCurrentPagerDestination(route: T): Boolean {
    return discoverDestination.indexOfFirst { index ->
        return@indexOfFirst index.route == route
    }.let { current ->
        return@let this@isCurrentPagerDestination?.currentPage == current
    }
}

suspend fun <T : Any> PagerState?.navigateToPagerRoute(route: T) {
    return this@navigateToPagerRoute?.animateScrollToPage(
        page = discoverDestination.indexOfFirst { index ->
            return@indexOfFirst index.route == route
        },
    ) ?: Unit
}