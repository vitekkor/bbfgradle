// Original bug: KT-15917

fun testFn(f: () -> Number) {}

fun main() {
    testFn({ 1 }) // ok
    val f = { 1 } // f: () -> Number
    testFn(f) // works, even though types differ
}
