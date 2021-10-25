// Original bug: KT-7363

interface A<T>
interface B<T> : A<A<T>>

fun foo(x : B<*>) {
    bar(x) // Type inference failed: 'T' cannot capture '*'. Only top-level type projections can be captured
}

fun <T> bar(x : A<A<T>>) { }
