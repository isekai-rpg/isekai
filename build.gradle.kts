plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.santio"
version = "1.0.0"

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {
    implementation("dev.hollowcube:minestom-ce:78cb62fa72")
    implementation("org.slf4j:slf4j-jdk14:2.0.11")

    // http client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("ru.gildor.coroutines:kotlin-coroutines-okhttp:1.0")

    // dotenv
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // kotlinx 
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")

    // mccoroutine
    implementation("com.github.shynixn.mccoroutine:mccoroutine-minestom-api:2.14.0")
    implementation("com.github.shynixn.mccoroutine:mccoroutine-minestom-core:2.14.0")

    // adventure api
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}
