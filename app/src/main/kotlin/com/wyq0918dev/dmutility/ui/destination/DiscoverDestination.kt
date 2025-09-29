package com.wyq0918dev.dmutility.ui.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kyant.capsule.ContinuousCapsule
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.ui.navigation.DashboardDestination
import com.wyq0918dev.dmutility.ui.navigation.EcosedKitDestination
import com.wyq0918dev.dmutility.ui.navigation.discoverDestination
import com.wyq0918dev.dmutility.ui.theme.CapsuleEdgePadding
import com.wyq0918dev.dmutility.ui.theme.CapsuleHeight
import com.wyq0918dev.dmutility.ui.theme.CapsuleIndent
import com.wyq0918dev.dmutility.ui.theme.CapsuleWidth
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.NoOnClick
import com.wyq0918dev.dmutility.ui.utils.navigateToPagerRoute
import com.wyq0918dev.dmutility.ui.utils.isCurrentPagerDestination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverDestination(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val pageState = rememberPagerState { discoverDestination.size }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                CenterAlignedTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    title = {
                        Text(text = stringResource(R.string.destination_discover))
                    },
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(start = CapsuleEdgePadding)
                                .width(width = CapsuleWidth)
                                .height(height = CapsuleHeight)
                                .clip(shape = ContinuousCapsule)
                                .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
                                .clickable(onClick = {}),
                            contentAlignment = Alignment.Center,
                        ) {
                            Row(
                                modifier = Modifier.wrapContentSize(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    modifier = Modifier.size(size = 20.dp),
                                )
                                Text(
                                    text = stringResource(id = R.string.discover_topbar_search),
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(start = 6.dp),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    },
                    actions = {
                        Row(
                            modifier = Modifier
                                .padding(end = CapsuleEdgePadding)
                                .height(height = CapsuleHeight)
                                .width(width = CapsuleWidth)
                                .clip(shape = ContinuousCapsule)
                                .background(color = MaterialTheme.colorScheme.surfaceContainerHighest),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(weight = 1f)
                                    .fillMaxSize()
                                    .clickable(onClick = NoOnClick),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MoreHoriz,
                                    contentDescription = null,
                                )
                            }
                            VerticalDivider(
                                modifier = Modifier
                                    .padding(vertical = CapsuleIndent)
                                    .wrapContentWidth()
                                    .fillMaxHeight(),
                            )
                            Box(
                                modifier = Modifier
                                    .weight(weight = 1f)
                                    .fillMaxSize()
                                    .clickable(onClick = NoOnClick),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                )
                TabRow(
                    selectedTabIndex = pageState.currentPage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                ) {
                    discoverDestination.forEach { destination ->
                        val isCurrent = pageState.isCurrentPagerDestination(
                            route = destination.route,
                        )
                        Tab(
                            selected = isCurrent,
                            onClick = {
                                if (!isCurrent) coroutineScope.launch {
                                    pageState.navigateToPagerRoute(route = destination.route)
                                }
                            },
                            text = {
                                Text(text = stringResource(id = destination.label))
                            },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            userScrollEnabled = false,
        ) { page ->
            when (discoverDestination[page].route) {
                DashboardDestination -> DashboardDestination(
                    popBackStack = {
                        navController?.popBackStack()
                    },
                    animateToEcosed = {
                        coroutineScope.launch {
                            pageState.navigateToPagerRoute(
                                route = EcosedKitDestination
                            )
                        }
                    },
                )

                EcosedKitDestination -> EcosedKitDestination(
                    animateToDashboard = {
                        coroutineScope.launch {
                            pageState.navigateToPagerRoute(
                                route = DashboardDestination
                            )
                        }
                    },
                )

                else -> Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "未知页面",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DiscoverDestinationPreview() {
    DMUtilityTheme {
        DiscoverDestination()
    }
}