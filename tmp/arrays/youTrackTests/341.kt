// Original bug: KT-27731

@Deprecated("", ReplaceWith("+b"))
fun foo(b: Bar) {}

class Bar {
    operator fun unaryPlus() {}
}

fun main(args: Array<String>) {
    val b = Bar()
    println(foo(b)) // -> println(+b)
    foo(b)          // -> empty line
}
