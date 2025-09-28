package com.wyq0918dev.dmutility.ui

import android.content.Context
import android.os.Build
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlutterDash
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.imageloading.rememberDrawablePainter
import com.kyant.capsule.ContinuousCapsule
import com.kyant.capsule.ContinuousRoundedRectangle
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.ui.page.EcosedKitPage
import com.wyq0918dev.dmutility.ui.theme.CapsuleEdgePadding
import com.wyq0918dev.dmutility.ui.theme.CapsuleHeight
import com.wyq0918dev.dmutility.ui.theme.CapsuleIndent
import com.wyq0918dev.dmutility.ui.theme.CapsuleWidth
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.NoOnClick
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object Settings

@Serializable
data object Installer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityMain() {

    val appDestination = arrayListOf(
        AppDestination(
            label = "Home",
            route = Home,
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
        ),
        AppDestination(
            label = "Settings",
            route = Settings,
            icon = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings,
        ),
    )
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val customNavSuiteType: NavigationSuiteType = with(
        receiver = currentWindowAdaptiveInfo(),
    ) {
        return@with when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> NavigationSuiteType.NavigationBar
            WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationRail
            WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
            else -> NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                adaptiveInfo = this@with
            )
        }
    }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            appDestination.forEach { destination ->
                val isCurrent: Boolean = currentDestination?.hierarchy?.any {
                    return@any it.hasRoute(route = destination.route::class)
                } == true
                item(
                    icon = {
                        Icon(
                            imageVector = if (isCurrent) {
                                destination.selectedIcon
                            } else {
                                destination.icon
                            },
                            contentDescription = destination.label,
                        )
                    },
                    modifier = Modifier.wrapContentSize(),
                    label = {
                        Text(text = destination.label)
                    },
                    selected = isCurrent,
                    onClick = {
                        navController.navigate(
                            route = destination.route,
                        ) {
                            popUpTo(
                                id = navController.graph.findStartDestination().id,
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = false,
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        layoutType = customNavSuiteType,
    ) {
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable<Home> {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                        Text("Hello World")
                    }
                }
            }
            composable<Settings> {
                UnderLayer()
            }
            composable<Installer> {
            }
        }
    }



}

@Preview
@Composable
private fun ActivityMainPreview() {
    DMUtilityTheme {
        ActivityMain()
    }
}








@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnderLayer(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val pageState = rememberPagerState(
        pageCount = { appDestination.size },
    )
    var showDialog by remember {
        mutableStateOf(value = false)
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ULTopBar()
        },
//        bottomBar = {
//            BottomAppBar(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .clip(
//                        shape = ContinuousRoundedRectangle(
//                            topStart = 16.dp,
//                            topEnd = 16.dp,
//                        ),
//                    ),
//                containerColor = Color(color = 0xff787493),
//                actions = {
//                    IconButton(
//                        onClick = {
//                            showDialog = true
//                        },
//                    ) {
//                        if (showDialog) AlertDialog(
//                            onDismissRequest = {
//                                showDialog = false
//                            },
//                            confirmButton = {
//                                TextButton(
//                                    onClick = {
//                                        showDialog = false
//                                    },
//                                ) {
//                                    Text(text = "确定")
//                                }
//                            },
//                            icon = {
//                                Icon(
//                                    imageVector = Icons.Filled.Android,
//                                    contentDescription = null,
//                                )
//                            },
//                            iconContentColor = AndroidGreen,
//                            title = {
//                                Text(text = "Android")
//                            },
//                            text = {
//                                Text(text = "Android API ${Build.VERSION.SDK_INT}")
//                            },
//                        )
//                        Icon(
//                            imageVector = Icons.Filled.Android,
//                            contentDescription = null,
//                            tint = AndroidGreen,
//                        )
//                    }
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        text = "Android",
//                        style = MaterialTheme.typography.titleMedium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                        color = Color.White,
//                        fontSize = 16.sp,
//                        textAlign = TextAlign.Left,
//                    )
//                },
//                floatingActionButton = {
//
//                },
//            )
//        },
//        floatingActionButton = {
//            Row {
//                NavBlock(
//                    modifier = Modifier.padding(end = 4.dp),
//                    pageState = pageState,
//                )
//                HomeFAB(
//                    modifier = Modifier.padding(start = 4.dp),
//                    popBackStack = {},
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End,
//        containerColor = AppBackground,
    ) { innerPadding ->
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            userScrollEnabled = false,
        ) { page ->
            when (appDestination[page].route) {
                DashboardPage -> DashboardPage(
                    popBackStack = {},
                    animateToEcosed = {
                        coroutineScope.launch {
                            pageState.animateToRoute(
                                route = EcosedKitPage
                            )
                        }
                    },
                )

                EcosedKitPage -> EcosedKitPage(
                    animateToDashboard = {
                        coroutineScope.launch {
                            pageState.animateToRoute(
                                route = DashboardPage
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
fun UnderLayerPreview() {
    DMUtilityTheme {
        UnderLayer()
    }
}

@Composable
fun NavBlock(
    modifier: Modifier = Modifier,
    pageState: PagerState? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    Surface(
        modifier = modifier
            .wrapContentSize()
            .sizeIn(minWidth = 80.dp),
        shape = ContinuousRoundedRectangle(size = 16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .defaultMinSize(minHeight = 56.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            appDestination.forEach { page ->
                val isCurrent = pageState?.isCurrentDestination(route = page.route)
                IconButton(
                    onClick = {
                        if (isCurrent == false) coroutineScope.launch {
                            pageState.animateToRoute(route = page.route)
                        }
                    },
                    modifier = Modifier.wrapContentSize(),
                ) {
                    val iconAlpha: Float by animateFloatAsState(
                        targetValue = if (isCurrent == true) 1f else 0.5f,
                        animationSpec = spring(
                            dampingRatio = 0.8f,
                            stiffness = 200f,
                        ),
                    )
                    Icon(
                        imageVector = if (isCurrent == true) {
                            page.selectedIcon
                        } else {
                            page.icon
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize()
                            .alpha(alpha = iconAlpha),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NavBlockPreview() {
    DMUtilityTheme {
        NavBlock()
    }
}

@Composable
fun HomeFAB(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit = NoOnClick,
) {
    ExtendedFloatingActionButton(
        text = {
            Text(text = "返回")
        },
        icon = {
            Icon(
                imageVector = Icons.TwoTone.Home,
                contentDescription = null,
            )
        },
        onClick = popBackStack,
        modifier = modifier
            .wrapContentSize()
            ,
        shape = ContinuousRoundedRectangle(size = 16.dp),
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    )
}

@Preview
@Composable
fun HomeFABPreview() {
    DMUtilityTheme {
        HomeFAB()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ULActionBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
) {
    ElevatedCard (
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ULTopBar(
    modifier: Modifier = Modifier,
) {
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
                Text(
                    text = "Treble",
//                    color = Color.White,
                )
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
//                            tint = Color(color = 0xff8E8E9E)
                        )
                        Text(
                            text = "搜索",
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 6.dp),
                            fontSize = 13.sp,
//                            color = Color(color = 0xff8E8E9E),
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
//                            tint = Color(color = 0xff8E8E9E),
                        )
                    }
                    VerticalDivider(
                        modifier = Modifier
                            .padding(vertical = CapsuleIndent)
                            .wrapContentWidth()
                            .fillMaxHeight(),
//                        color = Color(color = 0xff8E8E9E),
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
//                            tint = Color(color = 0xff8E8E9E),
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
        )
//        HorizontalDivider(
//            modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = Color.Black.copy(
//                alpha = 0.2f
//            )
//        )
    }
}

@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit = NoOnClick,
    animateToEcosed: () -> Unit = NoOnClick,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
    ) {
        Text(
            text = "核心服务",
            modifier = Modifier.padding(
                start = 30.dp,
                top = 30.dp,
                end = 30.dp,
                bottom = 15.dp,
            ),
            fontSize = 14.sp,
//            color = Color(
//                color = 0xFF8E8E9E,
//            ),
        )
        MPPlayer(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            popBackStack = popBackStack,
            animateToFlutter = animateToEcosed,
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = 16.dp, top = 15.dp, end = 16.dp, bottom = 15.dp
                ),
            shape = ContinuousRoundedRectangle(size = 16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHighest,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Row {
                    Text(
                        text = "互联互通",
                        fontSize = 14.sp,
//                        color = Color(
//                            color = 0xFF8E8E9E,
//                        ),
                    )
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                ) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(size = 40.dp)
                                .clip(shape = RoundedCornerShape(size = 35.dp))
                                .background(color = Color(color = 0xFF8E8E9E))
                                .clickable(onClick = {}),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                            )
                        }
                        Text(
                            text = "app",
                            fontSize = 15.sp,
//                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(size = 40.dp)
                                .clip(shape = RoundedCornerShape(size = 35.dp))
                                .background(color = Color(color = 0xFF8E8E9E))
                                .clickable(onClick = {}),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                            )
                        }
                        Text(
                            text = "app",
                            fontSize = 15.sp,
//                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(size = 40.dp)
                                .clip(shape = RoundedCornerShape(size = 35.dp))
                                .background(color = Color(color = 0xFF8E8E9E))
                                .clickable(onClick = {}),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                            )
                        }
                        Text(
                            text = "app",
                            fontSize = 15.sp,
//                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }


                }
            }
        }
        Text(
            text = "常用应用",
            modifier = Modifier.padding(
                start = 30.dp,
                top = 30.dp,
                end = 30.dp,
                bottom = 15.dp,
            ),
            fontSize = 14.sp,
//            color = Color(
//                color = 0xFF8E8E9E,
//            ),
        )
        AppsGrid(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            ),
            list = miniProgramList,
        )
    }
}

@Preview
@Composable
fun DashboardPagePreview() {
    DMUtilityTheme {
        DashboardPage()
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MPPlayer(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit = NoOnClick,
    animateToFlutter: () -> Unit = NoOnClick,
) {
    val context: Context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 90.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 1f),
        ) {
            AppItem(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxSize(),
                onLaunch = popBackStack,
                style = AppItemStyle.Image,
                appIcon = rememberDrawablePainter(
                    drawable = AppCompatResources.getDrawable(
                        context,
                        R.mipmap.ic_launcher,
                    ),
                ),
                appName = "TrebleKit",
            )
            AppItem(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxSize(),
                onLaunch = {},
                style = AppItemStyle.Image,
                appIcon = rememberDrawablePainter(
                    drawable = AppCompatResources.getDrawable(
                        context,
                        R.mipmap.ic_ebkit,
                    ),
                ),
                appName = "EbKit",
            )
        }
        RecentPlayer(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(start = 16.dp)
                .fillMaxSize(),
            animateToFlutter = animateToFlutter,
        )
    }
}

@Preview
@Composable
fun MPPlayerPreview() {
    DMUtilityTheme {
        MPPlayer()
    }
}

@Composable
fun RecentPlayer(
    modifier: Modifier = Modifier,
    animateToFlutter: () -> Unit = NoOnClick,
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ElevatedCard(
            modifier = Modifier.height(height = 60.dp).fillMaxWidth(),
            shape = ContinuousCapsule,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            onClick = animateToFlutter,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.FlutterDash,
                        contentDescription = null,
                        modifier = Modifier.size(size = 30.dp),
//                    tint = Color(color = 0xFF8E8E9E)
                    )
                    Text(
                        text = "暂无内容",
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 10.dp),
                        fontSize = 16.sp,
//                    color = Color(color = 0xFF8E8E9E),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Text(
            text = "Ecosed",
            fontSize = 15.sp,
//            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun RecentPlayerPreview() {
    DMUtilityTheme {
        RecentPlayer()
    }
}

data class MiniProgramItem(
    /** 名称 */
    val title: String,
    /** 图标 */
    val icon: String,
)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppsGrid(
    modifier: Modifier = Modifier,
    list: ArrayList<MiniProgramItem>,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 180.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp),
        columns = GridCells.Fixed(count = 4),
        userScrollEnabled = false,
    ) {
        items(items = list) { item ->
            Box {
                AppItem(
                    style = AppItemStyle.Image,
                    appIcon = rememberImagePainter(data = item.icon),
                    appName = item.title,
                )
            }
        }
    }
}

val miniProgramList: ArrayList<MiniProgramItem> = arrayListOf(
    MiniProgramItem(
        title = "饿了么",
        icon = "https://img0.baidu.com/it/u=2625005847,2716895016&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        title = "美图",
        icon = "https://img1.baidu.com/it/u=1752963805,4078506746&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        title = "滴滴",
        icon = "https://img0.baidu.com/it/u=1068101613,1323308017&fm=253&fmt=auto&app=138&f=PNG?w=200&h=200"
    ),
    MiniProgramItem(
        title = "青橘单车",
        icon = "https://img0.baidu.com/it/u=195120191,2939897897&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
    MiniProgramItem(
        title = "斗地主",
        icon = "https://img2.baidu.com/it/u=926635057,1451495262&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
    MiniProgramItem(
        title = "羊城通",
        icon = "https://img2.baidu.com/it/u=2751300851,4181594410&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        title = "美图秀秀",
        icon = "https://img1.baidu.com/it/u=417359459,147216874&fm=253&fmt=auto&app=138&f=PNG?w=200&h=200"
    ),
    MiniProgramItem(
        title = "拼多多",
        icon = "https://img2.baidu.com/it/u=620052409,134315960&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
)

enum class AppItemStyle {
    Image, Icon,
}

@Composable
fun AppItem(
    modifier: Modifier = Modifier,
    onLaunch: () -> Unit = NoOnClick,
    style: AppItemStyle,
    appIcon: Painter,
    appName: String,
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(size = 60.dp)
                .clip(shape = RoundedCornerShape(size = 35.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
                .clickable(onClick = onLaunch),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = appIcon,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = when (style) {
                    AppItemStyle.Image -> Modifier
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(
                                size = 35.dp,
                            ),
                        )

                    AppItemStyle.Icon -> Modifier.size(
                        size = 30.dp,
                    )
                }
            )
        }
        Text(
            text = appName,
            fontSize = 15.sp,
//            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun AppItemPreview() {
    DMUtilityTheme {
        AppItem(
            style = AppItemStyle.Icon,
            appIcon = painterResource(id = R.drawable.baseline_preview_24),
            appName = "Preview",
        )
    }
}