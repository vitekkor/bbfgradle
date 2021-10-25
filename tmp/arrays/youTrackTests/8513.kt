// Original bug: KT-20349

package test

fun Int.writeln() = println(this)

fun foo(x1: Int, x2: Int, x3: Int, x4: Int, x5: Int, fn: (Int) -> Unit) {
    fn(x1 + x2 + x3 + x4 + x5)
}

fun test() {
    foo(1, 2, 3, 4, x5 = 5) { it.writeln() } // (*)
}
