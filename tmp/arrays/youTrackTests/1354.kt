// Original bug: KT-44138

// WITH_RUNTIME

val unsigned = 0x8fffffffU
val good = "123 " + unsigned
val bad = "123 " + 0x8fffffffU

fun box(): String {
    if (good != "123 2415919103") return "good: '$good'"
    if (bad != "123 2415919103") return "bad: '$bad'"
    return "OK"
}
