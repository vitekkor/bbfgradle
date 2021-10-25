// Original bug: KT-25812

open class Foo(open val str: String) {
    init {
        println(str.length) // NPE here, str is null
    }
}

class Bar(override val str: String): Foo(str)

fun main(args: Array<String>) {
    Foo("") // OK here
    Bar("") // NPE here
}
