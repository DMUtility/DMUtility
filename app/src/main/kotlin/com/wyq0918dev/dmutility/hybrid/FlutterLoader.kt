package com.wyq0918dev.dmutility.hybrid

import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/** Flutter引擎ID */
private const val ENGINE_ID: String = "dm_utility_flutter"

/**
 * 初始化Flutter引擎
 */
fun Application.loadFlutter(): FlutterEngine {
    return FlutterEngine(this@loadFlutter).let { engine ->
        val entry = DartExecutor.DartEntrypoint.createDefault()
        engine.dartExecutor.executeDartEntrypoint(entry)
        FlutterEngineCache.getInstance().put(ENGINE_ID, engine)
        return@let engine
    }
}

/**
 * 加载Flutter片段
 */
fun loadFlutterFragment(): FlutterFragment {
    return FlutterFragment
        .withCachedEngine(ENGINE_ID)
        .renderMode(RenderMode.texture)
        .build()
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