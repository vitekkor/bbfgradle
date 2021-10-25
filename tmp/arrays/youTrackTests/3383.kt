// Original bug: KT-37630

fun <T> bar(timeMillis: Long) = null as T

fun foo(x: Int) {
    if (true) {
        bar(3 * 1000) // OK in OI, type mismatch in NI (Required: Long, Found: Int)
    } else true
}
