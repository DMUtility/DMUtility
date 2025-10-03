package com.wyq0918dev.dmutility

import android.os.Build
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.wyq0918dev.dmutility.hybrid.FLOAT_ENGINE_ID
import io.flutter.embedding.android.FlutterActivity

class FloatFlutterActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    override fun getCachedEngineId(): String? {
        return FLOAT_ENGINE_ID
    }
}