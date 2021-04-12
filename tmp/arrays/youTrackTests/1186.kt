// Original bug: KT-16520

class A {
    operator fun set(
            i1: Int,
            i2: Int = 1,
            v: Int
    ) {
        println(i2)
    }
}

fun main(args: Array<String>) {
    A()[1] = 1 // prints "0", expected "1"
}

