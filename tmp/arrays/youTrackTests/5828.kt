// Original bug: KT-32037

fun coerceToUnit(f: () -> Unit) {}

class Inv<T>

fun <K> builder(block: Inv<K>.() -> Unit): K = TODO()

fun test() {
    coerceToUnit {
        builder {} // Error in NI, OK in OI
    }
}
