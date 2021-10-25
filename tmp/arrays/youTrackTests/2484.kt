// Original bug: KT-40382

import kotlin.test.*

inline class Z(val s: String)

fun box(): String {
    val a = Z("a")
    val b = Z("b")

    val equals = Z::equals
    assertTrue(equals.call(a, a))
    assertFalse(equals.call(a, b))

    val hashCode = Z::hashCode
    assertEquals(a.s.hashCode(), hashCode.call(a))

    val toString = Z::toString
    assertEquals("Z(s=${a.s})", toString.call(a))

    return "OK"
}
