package com.wyq0918dev.dmutility.ui.destination

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wyq0918dev.dmutility.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDestination(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            BannerCarousel() // 500x300px
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BannerCarousel() {
    val images = arrayListOf(
        R.drawable.victory,
        R.drawable.shandong,
        R.drawable.victory,
    )
    val state = rememberCarouselState { images.count() }
    HorizontalMultiBrowseCarousel(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 8.dp),
        preferredItemWidth = 500.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { item ->
        Image(
            painter = painterResource(id = images[item]),
            contentDescription = null,
            modifier = Modifier
                .height(height = 200.dp)
                .maskClip(shape = MaterialTheme.shapes.extraLarge),
            contentScale = ContentScale.Crop
        )
    }
}