// Original bug: KT-14746

import kotlin.concurrent.thread

class Foo(var value: String)

fun main(args: Array<String>) {
    var foo = Foo("first")
    thread {
        foo = Foo("second")
    }
    println(foo.value) // <-- I assume either "first" or "second" is printed out (no NPE should happen)
}
