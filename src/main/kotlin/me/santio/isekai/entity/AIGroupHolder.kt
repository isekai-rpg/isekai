package me.santio.isekai.entity

import net.minestom.server.entity.ai.EntityAIGroup

interface AIGroupHolder {
    fun aiGroup(): EntityAIGroup
}