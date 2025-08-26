package com.wyq0918dev.dmutillity

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmutility.app.generated.resources.Res
import dmutility.app.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember {
            mutableStateOf(value = false)
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {

                    },
                    title = {
                        Text(text = "树莓次元")
                    },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        showContent = !showContent
                    },
                ) {
                    Text(text = "Click me!")
                }
                AnimatedVisibility(visible = showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(
                                resource = Res.drawable.compose_multiplatform
                            ),
                            contentDescription = null,
                        )
                        Text(text = "Compose: $greeting")
                    }
                }
            }
        }
    }
}