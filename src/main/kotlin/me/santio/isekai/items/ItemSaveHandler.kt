package me.santio.isekai.items

import me.santio.isekai.io.JSON
import java.io.File

class ItemSaveHandler(private val directory: File) {

    fun save(item: Item) {
        directory.mkdirs()

        val file = File(directory, "${item.fileName}.json")
        if (!file.exists()) file.createNewFile()

        file.writeText(JSON.encodeToString(Item.serializer(), item))
    }

    fun load(): List<Item> {
        return directory.listFiles()?.map {
            JSON.decodeFromString(Item.serializer(), it.readText())
        } ?: emptyList()
    }

}