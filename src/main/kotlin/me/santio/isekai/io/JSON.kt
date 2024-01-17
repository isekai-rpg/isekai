package me.santio.isekai.io

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import me.santio.isekai.io.serializer.MaterialSerializer
import me.santio.isekai.items.Item
import me.santio.isekai.items.types.Generic
import me.santio.isekai.items.types.Sword
import net.minestom.server.item.Material

val JSON = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
    serializersModule = SerializersModule {
        contextual(Material::class, MaterialSerializer)
        polymorphic(Item::class) {
            subclass(Sword::class, Sword.serializer())
            subclass(Generic::class, Generic.serializer())
        }
    }
}