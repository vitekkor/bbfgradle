// Original bug: KT-31360


@DslMarker
annotation class MyDsl

@MyDsl
interface Scope<A, B> {
    val something: A
    val value: B
}

val <T> Scope<*, T>.property: T get() = value // 'val value: T' can't be called in this context by implicit receiver. Use the explicit one if necessary

fun scoped1(block: Scope<Int, String>.() -> Unit) {
}

fun scoped2(block: Scope<*, String>.() -> Unit) {
}

fun f() {
    scoped1 {
        println(value)   // works!
        println(property) // works!
    }
    scoped2 {
        println(value)  // 'val value: String' can't be called in this context by implicit receiver. Use the explicit one if necessary
        println(property)  // doesn't work as well
    }
}
