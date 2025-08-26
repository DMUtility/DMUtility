package com.wyq0918dev.dmutility

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dmutility.composeapp.generated.resources.Res
import dmutility.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() {
    application {
        val state: WindowState = rememberWindowState(
            position = WindowPosition.Aligned(
                alignment = Alignment.Center
            ),
            size = DpSize(
                width = 1200.dp,
                height = 650.dp,
            ),
        )
        Window(
            onCloseRequest = {
                exitApplication()
            },
            state = state,
            title = stringResource(resource = Res.string.app_name),
        ) {
            App()
        }
    }
}