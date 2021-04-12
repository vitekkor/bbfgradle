// Original bug: KT-35697

import kotlin.math.roundToLong

data class Entry(var size: Int, var sum: Double)

fun main() {
    val entries = mutableMapOf<String, Entry>()

    // ...

    entries.forEach { term, (size, sum) ->
        println("$term\t${sum.roundToLong()};$size")
    }
}

