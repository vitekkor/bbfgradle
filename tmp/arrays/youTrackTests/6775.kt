// Original bug: KT-10723

fun <T> List<T>?.foo() {}
@JvmName("f1")
fun <T> List<T>.foo() {}

fun List<Int>.bar() {}
@JvmName("f2")
fun List<Int>?.bar() {}

fun test(a: List<Int>) {
    a.foo() // CANNOT_COMPLETE_RESOLVE error
    a.bar() // ok
}
