package com.wyq0918dev.dmutility.hybrid

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wyq0918dev.dmutility.FloatFlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup

/** Flutter引擎ID */
const val EMBED_ENGINE_ID: String = "dm_utility_flutter_embed"
const val FLOAT_ENGINE_ID: String = "dm_utility_flutter_float"
const val LOADER_TAG: String = "FlutterLoader"

/**
 * 初始化Flutter引擎
 */
fun Application.loadFlutter() {
    val engines = FlutterEngineGroup(this@loadFlutter)
    arrayListOf(EMBED_ENGINE_ID, FLOAT_ENGINE_ID).forEach { engineId ->
        engines.createAndRunDefaultEngine(this@loadFlutter).let { engine ->
            registerWith(engine = engine)
            FlutterEngineCache.getInstance().put(engineId, engine)
        }
    }
}

/**
 * 注册插件
 *
 * @param engine Flutter引擎
 */
private fun registerWith(engine: FlutterEngine) {
    try {
        engine.plugins.add(PlatformResources())
    } catch (e: Exception) {
        Log.e(
            LOADER_TAG,
            "Error registering plugin PlatformResources",
            e,
        )
    }
    try {
        engine.plugins.add(AndroidToFlutter())
    } catch (e: Exception) {
        Log.e(
            LOADER_TAG,
            "Error registering plugin AndroidToFlutter",
            e,
        )
    }
}

/**
 * 加载Flutter片段
 */
fun loadEmbedFlutterFragment(): FlutterFragment {
    return FlutterFragment.withCachedEngine(
        EMBED_ENGINE_ID,
    ).renderMode(
        RenderMode.texture, // 使用Texture渲染以嵌入视图
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
        adapter = object : FragmentStateAdapter(
            this@loadFlutterView,
        ) {
            /** 获取 Fragment 数量 */
            override fun getItemCount() = 1

            /** 创建 Fragment */
            override fun createFragment(position: Int): Fragment {
                return flutter ?: error(
                    message = "Flutter is null!",
                )
            }
        }
    }
}

fun Activity?.startFloatFlutter() {
    this@startFloatFlutter?.let { activity ->
        activity.startActivity(
            Intent(
                activity,
                FloatFlutterActivity().javaClass,
            ),
        )
    }
}