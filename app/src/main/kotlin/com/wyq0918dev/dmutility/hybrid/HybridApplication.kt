package com.wyq0918dev.dmutility.hybrid

import com.wyq0918dev.dmutility.base.BaseApplication

abstract class HybridApplication : BaseApplication() {

    /**
     * 初始化
     */
    override fun onCreate() {
        super.onCreate()
        // 初始化Flutter引擎
        loadFlutter()
    }
}