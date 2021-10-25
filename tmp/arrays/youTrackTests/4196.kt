// Original bug: KT-7410

fun foo(x: Array<Class<*>>) {}
fun foo(x: String) {}

fun main(args: Array<String>) {
    foo(arrayOf(Int::class.java)) // Error: None of the following functions can be called with the arguments supplied
}
