package com.wyq0918dev.dmutility.ui.destination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kyant.capsule.ContinuousRoundedRectangle
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.hybrid.FlutterView
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.NoOnClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcosedKitDestination(
    modifier: Modifier = Modifier,
    animateToDashboard: () -> Unit = NoOnClick,
) {
    val inspection: Boolean = LocalInspectionMode.current
    val inspectionModeText: String = stringResource(
        id = R.string.inspection_mode_text,
    )
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TPActionBar(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 8.dp,
            ),
            title = {
                Text(text = stringResource(R.string.destination_ecosedkit))
            },
            navigationIcon = {
                IconButton(
                    onClick = animateToDashboard,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
            shape = ContinuousRoundedRectangle(size = 16.dp),
        ) {
            if (!inspection) {
                FlutterView(
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Scaffold(
                    contentWindowInsets = WindowInsets(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("EcosedKit")
                            },
                        )
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = inspectionModeText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcosedKitDestinationPreview() {
    DMUtilityTheme {
        EcosedKitDestination()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TPActionBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 56.dp),
        shape = ContinuousRoundedRectangle(size = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        TopAppBar(
            title = title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            navigationIcon = navigationIcon,
            actions = {
                IconButton(onClick = NoOnClick) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                    )
                }
            },
            windowInsets = WindowInsets(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
        )
    }
}