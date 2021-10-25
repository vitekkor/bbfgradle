// Original bug: KT-35896

interface B<E, SC>

class Inv<T>

class Foo<T>(x: Int): B<T, Inv<T>>

fun <T1, T2, S> bar(list: T2, fn: (S) -> B<T1, T2>) {}

fun <T> foo(list: Inv<T>) {
    bar(list, ::Foo)
}
