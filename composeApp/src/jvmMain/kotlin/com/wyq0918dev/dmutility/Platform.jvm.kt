package com.wyq0918dev.dmutility

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun getDynamicColorScheme(isDarkTheme: Boolean): ColorScheme? = null
