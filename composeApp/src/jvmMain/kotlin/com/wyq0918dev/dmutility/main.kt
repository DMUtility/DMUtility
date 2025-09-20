package com.wyq0918dev.dmutility

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dmutility.composeapp.generated.resources.Res
import dmutility.composeapp.generated.resources.app_name
import dmutility.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() {
    application {
        Window(
            onCloseRequest = {
                exitApplication()
            },
            icon = painterResource(resource = Res.drawable.compose_multiplatform),
            title = stringResource(resource = Res.string.app_name),
        ) {
            App()
        }
    }
}