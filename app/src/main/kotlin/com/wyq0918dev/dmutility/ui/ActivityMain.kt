package com.wyq0918dev.dmutility.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.wyq0918dev.dmutility.ui.destination.DiscoverDestination
import com.wyq0918dev.dmutility.ui.destination.HomeDestination
import com.wyq0918dev.dmutility.ui.destination.SettingsDestination
import com.wyq0918dev.dmutility.ui.destination.ToolsDestination
import com.wyq0918dev.dmutility.ui.navigation.DiscoverDestination
import com.wyq0918dev.dmutility.ui.navigation.HomeDestination
import com.wyq0918dev.dmutility.ui.navigation.SettingsDestination
import com.wyq0918dev.dmutility.ui.navigation.ToolsDestination
import com.wyq0918dev.dmutility.ui.navigation.appDestination
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.isCurrentNavDestination
import com.wyq0918dev.dmutility.ui.utils.navigateToNavRoute

@Composable
fun ActivityMain() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val customNavSuiteType: NavigationSuiteType = with(
        receiver = currentWindowAdaptiveInfo(),
    ) {
        return@with when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> NavigationSuiteType.NavigationBar
            WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationRail
            WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
            else -> NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                adaptiveInfo = this@with
            )
        }
    }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            appDestination.forEach { destination ->
                val isCurrent: Boolean = navBackStackEntry.isCurrentNavDestination(
                    route = destination.route
                )
                item(
                    icon = {
                        Icon(
                            imageVector = if (isCurrent) {
                                destination.selectedIcon
                            } else {
                                destination.icon
                            },
                            contentDescription = stringResource(
                                id = destination.label,
                            ),
                        )
                    },
                    modifier = Modifier.wrapContentSize(),
                    label = {
                        Text(
                            text = stringResource(
                                id = destination.label,
                            ),
                        )
                    },
                    selected = isCurrent,
                    onClick = {
                        navController.navigateToNavRoute(
                            route = destination.route,
                        )
                    },
                    alwaysShowLabel = false,
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        layoutType = customNavSuiteType,
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeDestination,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable<HomeDestination> {
                HomeDestination(navController = navController)
            }
            composable<ToolsDestination> {
                ToolsDestination(navController = navController)
            }
            composable<DiscoverDestination> {
                DiscoverDestination(navController = navController)
            }
            composable<SettingsDestination> {
                SettingsDestination(navController = navController)
            }
        }
    }
}

@Preview
@Composable
private fun ActivityMainPreview() {
    DMUtilityTheme {
        ActivityMain()
    }
}