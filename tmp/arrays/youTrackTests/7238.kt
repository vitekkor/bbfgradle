// Original bug: KT-27434

inline class Foo(val value: Int) {
    constructor(x: Int, y: Int): this(x * y)
    fun foo() = value + 1
}

fun main(args: Array<String>) {
    println(Foo(1, 2).foo())
}
