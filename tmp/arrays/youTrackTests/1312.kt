// Original bug: KT-32358

// no inline modifier
fun <R> callIt(fn: () -> R): R = TODO()

fun smartIt(p1: String?, p2: String?) {
    p1 ?: callIt { TODO() }
    p1.length // smartcast
}
