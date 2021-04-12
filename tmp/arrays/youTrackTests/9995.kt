// Original bug: KT-10093

val Int.foo: Int get() = 4
val Any.foo: Int get() = 4

val Any.bar: Int get() = 4
val Int.bar: Int get() = 4

operator fun Int.invoke() {}

fun test() {
    with(1) {
        foo() // resolved to Int.foo + Int.invoke
        bar() // resolved to Any.bar + Int.invoke
    }
}
