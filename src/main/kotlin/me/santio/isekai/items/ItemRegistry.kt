package me.santio.isekai.items

import java.io.File

class ItemRegistry {

    private val directory = File("items")
    private val saveHandler = ItemSaveHandler(directory)

    private val _items: MutableList<Item> = mutableListOf()
    val items: List<Item>
        get() = _items

    fun register(item: Item) {
        _items.add(item)
    }

    fun save() {
        _items.forEach(saveHandler::save)
    }

    fun load() {
        _items.addAll(saveHandler.load())
    }

}