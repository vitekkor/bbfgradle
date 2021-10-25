// Original bug: KT-45714

import kotlin.test.assertEquals

fun test1() {
    val u = when (true) {
        true -> 42
        else -> 1.0
    }
    assertEquals(42, u) // false positive warning here, there is a proper super type â Comparable<*> & Number
    assertEquals(42, u as Number) // OK
}
