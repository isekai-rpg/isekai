package me.santio.isekai.items

import kotlinx.serialization.encodeToString
import me.santio.isekai.io.JSON
import java.io.File

class ItemSaveHandler(val directory: File) {

    inline fun <reified T: Item> save(item: T) {
        directory.mkdirs()

        val file = File(directory, "${item.fileName}.json")
        if (!file.exists()) file.createNewFile()

        file.writeText(JSON.encodeToString(item))
    }

    fun load(): List<Item> {
        return directory.listFiles()?.map {
            JSON.decodeFromString<Item>(it.readText())
        } ?: emptyList()
    }

}