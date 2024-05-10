package com.example.bama

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform