package com.wyq0918dev.dmutility

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.UiComposable

interface IPlatform {
    val name: String

    @Composable
    @UiComposable
    fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme?
}