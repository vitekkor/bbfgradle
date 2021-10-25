// Original bug: KT-21314

import java.util.ArrayList // becomes unused

fun test(strings: List<String>): MutableList<String> {
    val strings2 = strings
            .filter { it.length > 3 }
            .toMutableList() // (**)
    return strings2
}
