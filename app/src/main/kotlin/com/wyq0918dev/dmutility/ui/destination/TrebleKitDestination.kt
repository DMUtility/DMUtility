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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kyant.capsule.ContinuousRoundedRectangle
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.hybrid.FlutterView
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme
import com.wyq0918dev.dmutility.ui.utils.NoOnClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrebleKitDestination(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    animateToDashboard: () -> Unit = NoOnClick,
) {
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
                    Text(text = stringResource(id = R.string.treblekit_actionbar_title))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
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
                actions = {
                    var expanded by remember { mutableStateOf(value = false) }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("OpenInNew") },
                            onClick = {
//                                navController.navigateToNavRoute(route = TrebleActivityDestination)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Option 2") },
                            onClick = { expanded = false }
                        )
                    }
                    IconButton(onClick = { expanded = true }) {
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
fun TrebleKitDestinationPreview() {
    DMUtilityTheme {
        TrebleKitDestination()
    }
}