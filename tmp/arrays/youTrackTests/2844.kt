// Original bug: KT-7745

fun main(args: Array<String>) {
    f(1, b = 2, 3) // b is positionally the second, so naming it is OK
    // currently fails with "Error: Mixing named and positioned arguments is not allowed"
}

fun f(a: Int, b: Int, c: Int) {}
