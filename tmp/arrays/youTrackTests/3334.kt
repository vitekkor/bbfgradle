// Original bug: KT-39154

fun <T> materialize(): T = null as T

class Foo<out A> {
    fun <B> product(other: Foo<(A) -> B>) = materialize<Foo<B>>()

    fun <B, C, R> foo2(other1: Foo<B>, other2: Foo<C>, function: (A, B, C) -> R) {
        val x = product<R>(
            other1.product(
                other2.product(
                    bar {  c -> { b -> { a -> function(a, b, c) } } } // OK
                )
            )
        )
    }

    companion object {
        fun <A> bar(x: A) = materialize<Foo<A>>()
    }
}
