package me.santio.isekai.io.mojang

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnifiedMojangAPIResponse(
    val uuid: String,
    val username: String,
    @SerialName("username_history")
    val usernameHistory: List<UsernameHistory>,
    val textures: Textures,
    @SerialName("created_at")
    val createdAt: String? = null,
) {
    @Serializable
    data class UsernameHistory(
        val username: String,
        @SerialName("changed_at")
        val changedAt: String? = null
    )

    @Serializable
    data class Textures(
        val slim: Boolean,
        val custom: Boolean,
        @SerialName("raw")
        val skin: Skin
    )

    @Serializable
    data class Skin(
        val value: String,
        val signature: String,
    )
}
