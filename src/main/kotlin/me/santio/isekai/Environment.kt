package me.santio.isekai

enum class Environment {
    PROD,
    DEV;

    val isProd: Boolean
        get() = this == PROD
}