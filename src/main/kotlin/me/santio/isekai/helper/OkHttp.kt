package me.santio.isekai.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import me.santio.isekai.io.JSON
import okhttp3.Response

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T : Any> Response.fromJson(): Result<T> {
    if(body == null) {
        return Result.failure(NullPointerException("response body was null"))
    }

    if(!isSuccessful) {
        return Result.failure(IllegalStateException("response was not successful"))
    }

    return Result.success(JSON.decodeFromStream(
        body!!.byteStream()
    ))
}