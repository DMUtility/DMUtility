package com.wyq0918dev.dmutility

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform