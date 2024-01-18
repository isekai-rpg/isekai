package me.santio.isekai.entity

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.EntityType

abstract class Shootable(
    shooter: Entity,
    type: EntityType
): EntityProjectile(shooter, type)