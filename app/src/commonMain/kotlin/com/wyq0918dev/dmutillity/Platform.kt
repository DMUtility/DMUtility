package com.wyq0918dev.dmutillity

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform