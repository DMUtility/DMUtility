package com.wyq0918dev.dmutility

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AndroidPlatform : IPlatform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    @Composable
    override fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? {
        val context = LocalContext.current
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else null
    }
}