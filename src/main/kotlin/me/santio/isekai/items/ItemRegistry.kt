package me.santio.isekai.items

import java.io.File

class ItemRegistry {

    private val directory = File("items")
    private val saveHandler = ItemSaveHandler(directory)

    private val _items: MutableList<Item> = mutableListOf()
    val items: List<Item>
        get() = _items

    fun register(item: Item): ItemRegistry {
        _items.add(item)
        return this
    }

    fun save(): ItemRegistry {
        _items.forEach(saveHandler::save)
        return this
    }

    fun load(): ItemRegistry {
        _items.addAll(saveHandler.load())
        return this
    }

}