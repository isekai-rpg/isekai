package me.santio.isekai.data

import me.santio.isekai.io.mojang.UnifiedMojangAPIResponse
import java.util.*

data class PlayerData(

    val uuid: UUID,

    @Transient
    val mojang: UnifiedMojangAPIResponse

)
