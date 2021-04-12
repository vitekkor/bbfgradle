// Original bug: KT-39154

fun <T> materialize(): T = null as T

class Foo<out A> {
    fun <B> product(other: Foo<(A) -> B>) = materialize<Foo<B>>()

    fun <B, C, D, E, R> foo3(other1: Foo<B>, other2: Foo<C>, other3: Foo<D>, other4: Foo<E>, function: (A, B, C, D) -> R) {
        val x = product<R>(
            other1.product(
                other2.product(
                    other3.product(
                        // Required: Foo<(D) â (C) â (B) â (A) â R>
                        // Found: Foo<(D) â (C) â (B) â Any?>
                        bar { d -> { c -> { b -> { a -> function(a, b, c, d) } } } } // ERROR
                    )
                )
            )
        )
    }

    companion object {
        fun <A> bar(x: A) = materialize<Foo<A>>()
    }
}
