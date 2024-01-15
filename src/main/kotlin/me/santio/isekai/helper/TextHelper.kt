package me.santio.isekai.helper

fun String.wrap(width: Int): List<String> {
    val words = this.split(" ").toMutableList()
    val lines = mutableListOf("")

    while (words.isNotEmpty()) {
        val next = words.removeFirst()
        if (lines.last().length + next.length >= width) {
            lines.add(next)
        } else {
            lines[lines.lastIndex] += " $next"
        }
    }

    return lines.map { it.trim() }
}