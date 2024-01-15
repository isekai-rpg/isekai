package me.santio.isekai.io

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import me.santio.isekai.io.serializer.MaterialSerializer
import net.minestom.server.item.Material

val JSON = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
    serializersModule = SerializersModule {
        contextual(Material::class, MaterialSerializer)
    }
}