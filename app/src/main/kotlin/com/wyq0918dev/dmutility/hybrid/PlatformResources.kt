package com.wyq0918dev.dmutility.hybrid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import com.wyq0918dev.dmutility.R
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream

class PlatformResources : FlutterPlugin, MethodChannel.MethodCallHandler {

    /** 方法通道 */
    private lateinit var mChannel: MethodChannel

    /** 应用程序上下文 */
    private lateinit var mContext: Context

    /**
     * 附加到引擎
     */
    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        mContext = binding.applicationContext
        mChannel = MethodChannel(binding.binaryMessenger, PLATFORM_RESOURCES_CHANNEL)
        mChannel.setMethodCallHandler(this)
    }

    /**
     * 从引擎分离
     */
    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        mChannel.setMethodCallHandler(null)
    }

    /**
     * 方法调用
     */
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "freefeos" -> result.success(drawableToByteArray(id = R.drawable.ic_freefeos))
            "ecosedkit" -> result.success(drawableToByteArray(id = R.drawable.ic_ecosedkit))
            "ebkit" -> result.success(drawableToByteArray(id = R.drawable.ic_ebkit))
            else -> result.notImplemented()
        }
    }

    /**
     * 绘制Drawable为PNG格式二进制数据
     */
    private fun drawableToByteArray(id: Int): ByteArray {
        val drawable: Drawable? = ContextCompat.getDrawable(mContext, id)
        if (drawable != null) {
            val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        } else {
            return ByteArray(size = 0)
        }
    }

    /**
     * 伴生对象
     */
    private companion object {

        /** 平台资源插件通道名称 */
        const val PLATFORM_RESOURCES_CHANNEL: String = "platform_resources"
    }
}