package me.santio.isekai.io

import kotlinx.serialization.json.Json

val JSON = Json {
    ignoreUnknownKeys = true
    isLenient = true
}