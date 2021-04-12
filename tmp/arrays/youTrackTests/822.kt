// Original bug: KT-44623

@DslMarker
annotation class MyDslMarker

@MyDslMarker
interface MyDsl

fun something(
    scope: suspend MyDsl.() -> Unit,
) {
}

fun main() {
    something { }
}
