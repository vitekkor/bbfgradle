// Original bug: KT-11998

data class Foo(internal val bar: Boolean?)

fun main(args: Array<String>) {
    val f = Foo(true)
    // Smart cast of 'f.bar' to Boolean
    if (f.bar != null && f.bar) {
        println("bar was true")
    }
}
