package com.wyq0918dev.dmutility

import com.google.android.material.color.DynamicColors
import com.wyq0918dev.dmutility.hybrid.HybridApplication

class DMUtilityApp : HybridApplication() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this@DMUtilityApp)
    }
}