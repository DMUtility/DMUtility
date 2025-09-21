package com.wyq0918dev.dmutility

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

class JVMPlatform : IPlatform {
    override val name: String = "Java ${System.getProperty("java.version")}"

    @Composable
    override fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? {
        return null
    }
}