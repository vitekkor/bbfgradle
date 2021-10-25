// Original bug: KT-44259

fun interface FI {
    fun f(i: Int): String
}

fun a(fi: FI) {
    println(fi.f(1))
}

val str = a { i -> i.toString() }  // This should have an implement indicator (I in circle with arrow up) in the gutter, linking to `FI.f()`
