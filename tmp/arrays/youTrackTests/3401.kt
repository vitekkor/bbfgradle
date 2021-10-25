// Original bug: KT-37914

interface I

interface Foo<T> : I

class Bar<T>(val x: Foo<T>) : Foo<T>

fun <T, U : T> castToSubtype(obj: T) = obj as U

fun <T> select(vararg x: T) = x[0]

fun <T> materialize(): T = null as T

fun main() {
    select(
        materialize<Foo<Any>>(),
        Bar(
            castToSubtype(materialize<I>()) // NI: "required â Foo<Any>, found â I" afther the commit, OI â OK
        )
    )
}
