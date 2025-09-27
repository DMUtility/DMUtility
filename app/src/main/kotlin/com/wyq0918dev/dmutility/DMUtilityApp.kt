package com.wyq0918dev.dmutility

import android.content.Context
import android.os.Build
import com.google.android.material.color.DynamicColors
import com.wyq0918dev.dmutility.hybrid.HybridApplication
import org.lsposed.hiddenapibypass.HiddenApiBypass

class DMUtilityApp : HybridApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            HiddenApiBypass.addHiddenApiExemptions("L")
        }
    }

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this@DMUtilityApp)
    }
}