package com.wyq0918dev.dmutility

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.wyq0918dev.dmutility.base.ComposableActivity
import com.wyq0918dev.dmutility.ui.ActivityMain
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme

class MainActivity : ComposableActivity() {

    @Composable
    override fun Content() {
        DMUtilityTheme {
            ActivityMain()
        }
    }

    @Preview(
        device = "id:pixel_9",
        apiLevel = 36,
        showSystemUi = true,
        showBackground = true,
        wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    )
    @Composable
    private fun MainActivityPreview() {
        DMUtilityTheme {
            ActivityMain()
        }
    }
}