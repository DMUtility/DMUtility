package com.wyq0918dev.dmutility.hybrid

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import com.wyq0918dev.dmutility.base.BaseApplication

abstract class HybridApplication : BaseApplication() {

    /**
     * 初始化
     */
    override fun onCreate() {
        super.onCreate()
        // 初始化Flutter引擎并注册插件
        registerWith(engine = loadFlutter())
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
                TAG,
                "Error registering plugin PlatformResources",
                e,
            )
        }
        try {
            engine.plugins.add(AndroidToFlutter())
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Error registering plugin AndroidToFlutter",
                e,
            )
        }
    }

    /**
     * 伴生对象
     */
    private companion object {

        /** 日志标签 */
        const val TAG: String = "HybridApplication"
    }
}