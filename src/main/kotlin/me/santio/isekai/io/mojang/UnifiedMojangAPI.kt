package me.santio.isekai.io.mojang

import me.santio.isekai.helper.fromJson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await

class UnifiedMojangAPI {

    /**
     * Get a [UnifiedMojangAPIResponse] from a username
     * @param query username to query
     * @return [Result] of [UnifiedMojangAPIResponse]
     */
    suspend fun get(query: String): Result<UnifiedMojangAPIResponse> {
        val request = Request.Builder()
            .url(BASE_URL + query)
            .build()

        val response = client.newCall(request).await()
        return response.fromJson<UnifiedMojangAPIResponse>()
    }

    companion object {
        const val BASE_URL: String = "https://api.ashcon.app/mojang/v2/user/"

        private val client = OkHttpClient().newBuilder().build()
    }
}