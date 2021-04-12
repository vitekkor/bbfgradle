// Original bug: KT-10567

fun main(args: Array<String>) {
    val prop : Foo.() -> String = if (true) {
        Foo::a
    } else {
        Foo::b
    }
}

class Foo(val a: String, val b: String)
