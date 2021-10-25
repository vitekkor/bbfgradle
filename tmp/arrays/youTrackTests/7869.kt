// Original bug: KT-22654

fun foo() { throw RuntimeException() }
fun bar() {}

fun main(args: Array<String>) {
    try {
        foo()
    } finally {
        bar() // Put breakpoint here
    }
}
