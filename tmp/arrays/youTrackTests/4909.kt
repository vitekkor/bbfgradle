// Original bug: KT-34820

class R
class P

typealias F = R.(P) -> Unit

fun guess(): F? = TODO()
fun consume(f: F) {}

fun problem() {
    val p = guess()
    consume(p ?: {}) // Error in NI, ok in OI
}
