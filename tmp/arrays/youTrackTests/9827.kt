// Original bug: KT-11191

class A {
    operator fun get(i: Int) = 0.0

    operator fun set(i: Int, value: Double) {}
}

fun main(args: Array<String>) {
    val a = A()
    a[0]++
}
