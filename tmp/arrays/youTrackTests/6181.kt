// Original bug: KT-9493

import Color.*

enum class Color {
    RED,
    GREEN,
    BLUE
}

fun main(args: Array<String>) {
    val c: Color = BLUE
    
    when (c) {
        RED -> "red"
        GREEN -> "green"
        BLUE -> "blue"
    }.let { println(it) }
}
