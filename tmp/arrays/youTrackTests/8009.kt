// Original bug: KT-23790

fun listConsumer(x: () -> List<String>) {}

fun foo() {
    listConsumer { listOf() } // OK
    listConsumer(fun() = listOf()) // type argument for listOf() can't be inferred
}
