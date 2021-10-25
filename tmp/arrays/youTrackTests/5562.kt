// Original bug: KT-21314

import java.util.ArrayList // NB

fun test(strings: List<String>): MutableList<String> {
    val strings2 = ArrayList<String>()
    for (s in strings) { // (*)
        if (s.length > 3) {
            strings2.add(s)
        }
    }
    return strings2
}
