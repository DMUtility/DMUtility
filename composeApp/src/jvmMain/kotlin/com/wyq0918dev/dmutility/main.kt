package com.wyq0918dev.dmutility

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DMUtility",
    ) {
        App()
    }
}