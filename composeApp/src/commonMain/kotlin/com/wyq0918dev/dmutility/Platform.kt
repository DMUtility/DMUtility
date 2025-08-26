package com.wyq0918dev.dmutility

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getDynamicColorScheme(isDarkTheme: Boolean): ColorScheme?