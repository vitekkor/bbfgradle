// Original bug: KT-3537

class Foo {
    val bar = Bar()

    class Bar {
        fun f() = "hi!"
    }

    fun g() = bar.f()
}

fun main(args: Array<String>) {
    println(Foo().g())
}
