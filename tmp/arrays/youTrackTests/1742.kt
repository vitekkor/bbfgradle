// Original bug: KT-12451

fun main() {
    checkcast()
}

fun <T> ugly(value : Any?) = value as T

fun assertEquals(x: Any?, y: Any?) {
    if (x != y) throw AssertionError()
}

fun checkcast() {
    // assertEquals('c', ugly<Char>("c"))  // AE is thrown, but CCE should've happened earlier
}
