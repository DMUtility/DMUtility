package com.wyq0918dev.dmutillity

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
         return "Hello, ${platform.name}!"
    }
}