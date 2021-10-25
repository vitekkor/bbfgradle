// Original bug: KT-22877

import java.util.*

fun main() {
    val foo = TreeMap<Int, Int>({ o1, o2 -> o2.compareTo(o1) })
    val bar = TreeMap<Int, Int>({ o1, o2 -> o1.compareTo(o2) })
}
