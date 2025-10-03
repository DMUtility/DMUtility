package com.wyq0918dev.dmutility.hybrid

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine

class CustomPluginRegistrant {

    /**
     * 注册插件
     *
     * @param engine Flutter引擎
     */
    fun registerWith(engine: FlutterEngine) {
        arrayListOf(PlatformResources(), AndroidToFlutter()).forEach { plugin ->
            try {
                engine.plugins.add(plugin)
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    "Error registering plugin ${plugin.javaClass.name}",
                    e,
                )
            }
        }
    }

    companion object {
        const val TAG: String = "CustomPluginRegistrant"
    }
}