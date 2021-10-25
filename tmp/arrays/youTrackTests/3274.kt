// Original bug: KT-36818

fun <T> select(vararg x: T) = x[0]

fun main() {
    val x1 = select(0.1, null)
    val x2 = select(0.1, null)

    when (x1) {
        null -> throw Exception()
        in 0.0..1.0 -> {} // error, no smartcast from previous branch, OK in OI
        else -> throw Exception()
    }

    if (x2 == null) {
        throw Exception()
    } else if (x2 in 0.0..1.0) { // OK, there is smartcast
        println("1")
    }
}
