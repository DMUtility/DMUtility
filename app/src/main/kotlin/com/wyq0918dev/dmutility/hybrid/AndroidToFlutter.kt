package com.wyq0918dev.dmutility.hybrid

import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import java.util.concurrent.atomic.AtomicBoolean

class AndroidToFlutter : FlutterPlugin, EventChannel.StreamHandler {

    /** Flutter 事件通道 */
    private lateinit var mEventChannel: EventChannel

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        // 初始化事件通道
        mEventChannel = EventChannel(binding.binaryMessenger, "android_to_flutter")
        // 设置事件通道流处理
        mEventChannel.setStreamHandler(this@AndroidToFlutter)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        mEventChannel.setStreamHandler(null)
    }

    override fun onListen(
        arguments: Any?,
        events: EventChannel.EventSink?,
    ) {
        if (events != null) {
            mEventSink = events
        } else {
            Log.e("TAG", "EventSink 为空")
        }
        if (mListeningState.getAndSet(events != null)) {
            Log.w("TAG", "已在监听事件，忽略重复请求")
            return
        }
    }

    override fun onCancel(arguments: Any?) {
        if (mListeningState.getAndSet(false)) {
            mEventSink = null
        }
    }

    companion object {

        /** 事件通道事件接收器 */
        private var mEventSink: EventChannel.EventSink? = null

        /** 事件通道监听状态 */
        private val mListeningState = AtomicBoolean(false)

        /**
         * 发送数据到Flutter
         */
        fun sendData(data: String) {
            if (mListeningState.get()) {
                try {
                    mEventSink?.success(
                        mapOf(
                            "type" to "takeString",
                            "data" to data,
                        )
                    )
                } catch (e: Exception) {
                    mEventSink?.error(
                        "GODOT_EVENT_ERROR",
                        e.message,
                        null,
                    )
                }
            }
        }
    }
}