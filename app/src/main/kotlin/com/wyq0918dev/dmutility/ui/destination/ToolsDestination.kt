package com.wyq0918dev.dmutility.ui.destination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.ui.navigation.ContainerDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolsDestination(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.destination_tools))
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            ElevatedButton(
                onClick = {
                   navController?.navigate(route = ContainerDestination)
                }
            ) {
                Text(text = "Container")
            }
        }
    }
}