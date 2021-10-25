// Original bug: KT-29314

// WITH_RUNTIME

fun testUInt(x: UInt) =
    when (x) {
        0u -> "none"
        1u -> "one"
        2u -> "two"
        3u -> "three"
        else -> "many"
    }

fun box(): String {
    val t = testUInt(2u)
    if (t != "two") throw AssertionError("$t")

    return "OK"
}
