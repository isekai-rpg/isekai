package me.santio.isekai.io.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minestom.server.item.Material

object MaterialSerializer: KSerializer<Material> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Material", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Material {
        val materialName = decoder.decodeString()
            .lowercase()
            .replace(" ", "_")
            .removePrefix("minecraft:")

        return Material.fromNamespaceId("minecraft:$materialName")
            ?: throw SerializationException("Invalid material: minecraft:$materialName")
    }

    override fun serialize(encoder: Encoder, value: Material) {
        encoder.encodeString(
            value.name()
                .removePrefix("minecraft:")
                .lowercase()
        )
    }
}