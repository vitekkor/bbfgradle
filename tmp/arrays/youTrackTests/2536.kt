// Original bug: KT-32387

object Outer {
    val foo: String = Inner.bar

    object Inner { val bar: String = makeBar() }

    private fun makeBar() = "bar"
}

fun main() {
    Outer.Inner
    println(Outer.foo) // null
}
