package com.wyq0918dev.dmutility.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme

@Composable
fun FlutterView(modifier: Modifier = Modifier) {
    ViewFactory(modifier = modifier) { getFlutterView }
}

@Preview(showBackground = true)
@Composable
private fun FlutterViewPreview() {
    DMUtilityTheme {
        FlutterView()
    }
}