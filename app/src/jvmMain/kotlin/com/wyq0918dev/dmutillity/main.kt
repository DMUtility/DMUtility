package com.wyq0918dev.dmutillity

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dmutility.app.generated.resources.Res
import dmutility.app.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

fun main() {
    application {
        val state = rememberWindowState(
            position = WindowPosition.Aligned(
                alignment = Alignment.Center
            ) // 窗口居中显示
        )
        Window(
            onCloseRequest = {
                exitApplication()
            },
            state = state,
            title = "DMUtility",
            icon = painterResource(Res.drawable.compose_multiplatform),
        ) {
            App()
        }
    }
}