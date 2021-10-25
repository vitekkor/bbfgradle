// Original bug: KT-17692

fun main(args: Array<String>) {
fun foo(): Unit {}
assert(Unit.javaClass.equals(foo().javaClass)) // OK
assert(Unit.javaClass.equals(foo()::class.java)) // NullPointerException
}
