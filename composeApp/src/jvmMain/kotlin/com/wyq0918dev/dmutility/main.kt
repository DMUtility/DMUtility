package com.wyq0918dev.dmutility

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dmutility.composeapp.generated.resources.Res
import dmutility.composeapp.generated.resources.app_name
import dmutility.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    val state = rememberWindowState(
        position = WindowPosition.Aligned(
            alignment = Alignment.Center,
        ),
        size = DpSize(
            width = 800.dp,
            height = 600.dp,
        ),
    )
    Window(
        state = state,
        onCloseRequest = ::exitApplication,
        icon = painterResource(resource = Res.drawable.compose_multiplatform),
        title = stringResource(resource = Res.string.app_name),
    ) {
        DMUtilityApp()
    }
}