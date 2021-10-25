// Original bug: KT-25812

open class Foo(open val i: Int) {
    init {
        println(i)
    }
}

class Bar(override val i: Int) : Foo(i)

fun main(args: Array<String>) {
    Foo(42) // 42
    Bar(42) // 0
}
