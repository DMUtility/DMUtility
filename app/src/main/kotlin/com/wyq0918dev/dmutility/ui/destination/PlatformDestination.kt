package com.wyq0918dev.dmutility.ui.destination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.twotone.Category
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.wyq0918dev.dmutility.ui.navigation.DashboardDestination
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.navigateToPagerRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatformDestination(
    modifier: Modifier = Modifier,
    pageState: PagerState? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    var dropdownExpanded by remember { mutableStateOf(value = false) }
    var aboutExpanded by remember { mutableStateOf(value = false) }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                )
                .height(height = 56.dp),
            shape = ContinuousRoundedRectangle(size = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.platform_actionbar_title))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                pageState.navigateToPagerRoute(
                                    route = DashboardDestination
                                )
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    if (aboutExpanded) AlertDialog(
                        onDismissRequest = {
                            aboutExpanded = false
                        },
                        title = {
                            Text(text = "关于Treble平台")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.TwoTone.Category,
                                contentDescription = null,
                            )
                        },
                        confirmButton = {
                            TextButton(onClick = { aboutExpanded = false }) {
                                Text(text = "OK")
                            }
                        },
                        text = {
                            Text(text = "Treble平台是基于Dart和Kotlin自研的核心软件平台. 由FreeFEOS, EcosedKit和EbKit三大部分组成. 分别是核心组件, 应用层组件和平台能力桥接组件.")
                        },
                    )
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = {
                            dropdownExpanded = false
                        },
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "关于")
                            },
                            onClick = {
                                aboutExpanded = true
                                dropdownExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Option 2")
                            },
                            onClick = {
                                dropdownExpanded = false
                            }
                        )
                    }
                    IconButton(
                        onClick = {
                            dropdownExpanded = true
                        },
                    ) {
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
            when {
                LocalInspectionMode.current -> Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "EcosedKit")
                            },
                            windowInsets = WindowInsets()
                        )
                    },
                    contentWindowInsets = WindowInsets(),
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = stringResource(id = R.string.inspection_mode_text))
                    }
                }

                else -> FlutterView(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlatformDestinationPreview() {
    DMUtilityTheme {
        PlatformDestination()
    }
}