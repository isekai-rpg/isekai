package me.santio.isekai.helper.minestom

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player

fun Entity.triggerStatus(status: NamedEntityStatus) {
    triggerStatus(status.identifier)
}

// todo: make different status things because different
// mobs have their own status, possibly attach onto EntityType?
enum class NamedEntityStatus(val identifier: Byte) {
    /** Plays the death sound and death animation */
    DEATH(3),
    /** Spawns honey block fall particles at the entities feet */
    IN_HONEY(54);
}