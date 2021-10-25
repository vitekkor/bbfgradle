// Original bug: KT-11206

class A {
    operator fun get(i: Int, j: Int, k: Int, p: Int) = 1
    operator fun set(i: Int, j: Int, k: Int, p: Int, value: Int) {}
}

fun test(a: A) {
    a[0, 0, 0, 0]++
}
