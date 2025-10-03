package com.wyq0918dev.dmutility.ui.components

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.wyq0918dev.dmutility.R
import com.wyq0918dev.dmutility.common.IViewFactory
import com.wyq0918dev.dmutility.ui.theme.DMUtilityTheme

@Composable
fun ViewFactory(
    modifier: Modifier = Modifier,
    view: IViewFactory.() -> View? = { null },
) {
    val activity: Activity? = LocalActivity.current
    val inspection: Boolean = LocalInspectionMode.current
    val inspectionModeText: String = stringResource(
        id = R.string.inspection_mode_text,
    )
    AndroidView(
        factory = { context ->
            when {
                inspection -> TextView(context)
                activity != null && activity is IViewFactory -> {
                    (activity as? IViewFactory)?.view() ?: error(
                        message = "IViewFactory not implemented",
                    )
                }

                else -> View(context)
            }
        },
        modifier = modifier,
        update = { view ->
            if (inspection && view is TextView) {
                view.text = inspectionModeText
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ViewFactoryPreview() {
    DMUtilityTheme {
        ViewFactory()
    }
}