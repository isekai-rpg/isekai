package me.santio.isekai.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import me.santio.isekai.io.JSON
import okhttp3.Response

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T: Any> Response.fromJson(): T {
    if (this.body == null) error("Response body is null")
    return JSON.decodeFromStream<T>(this.body!!.byteStream())
}