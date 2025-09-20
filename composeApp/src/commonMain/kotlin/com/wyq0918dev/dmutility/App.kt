package com.wyq0918dev.dmutility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmutility.composeapp.generated.resources.Res
import dmutility.composeapp.generated.resources.app_name
import dmutility.composeapp.generated.resources.compose_multiplatform
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DMUtilityApp() {
    var showContent by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()





    MaterialTheme {

        NavigationSuiteScaffold(
            modifier = Modifier.fillMaxSize(),
            navigationSuiteItems = {
                item(
                    selected = true,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Home")
                    },
                   alwaysShowLabel = false
                )
                item(
                    selected = false,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Home")
                    },
                    alwaysShowLabel = false
                )
                item(
                    selected = false,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Home")
                    },
                    alwaysShowLabel = false
                )
                item(
                    selected = false,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Home")
                    },
                    alwaysShowLabel = false
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(resource = Res.string.app_name))
                        },
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer
//                        )
                    )
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues = innerPadding)
                        .verticalScroll(state = scrollState),
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
                                    resource = Res.drawable.compose_multiplatform,
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
}

@Preview
@Composable
private fun AppPreview() {
    DMUtilityApp()
}