package com.wyq0918dev.dmutility.hybrid

import android.app.Application
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup

/** Flutter引擎ID */
const val EMBED_ENGINE_ID: String = "dm_utility_flutter_embed"
const val FLOAT_ENGINE_ID: String = "dm_utility_flutter_float"

/**
 * 初始化Flutter引擎
 */
fun Application.loadFlutter() {
    FlutterEngineGroup(this@loadFlutter).let { group ->
        arrayListOf(EMBED_ENGINE_ID, FLOAT_ENGINE_ID).forEach { engineId ->
            group.createAndRunDefaultEngine(this@loadFlutter).let { engine ->
                CustomPluginRegistrant().registerWith(engine = engine)
                FlutterEngineCache.getInstance().put(engineId, engine)
            }
        }
    }
}

/**
 * 加载Flutter片段
 */
fun loadFlutterFragment(): FlutterFragment {
    return FlutterFragment.withCachedEngine(
        EMBED_ENGINE_ID,
    ).renderMode(
        RenderMode.texture,
    ).build()
}

/**
 * 加载Flutter视图
 */
fun FragmentActivity.loadFlutterView(
    flutter: FlutterFragment?,
): View {
    return ViewPager2(this@loadFlutterView).apply {
        isUserInputEnabled = false
        adapter = FlutterAdapter(
            activity = this@loadFlutterView,
            flutter = flutter
        )
    }
}