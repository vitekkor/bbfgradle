// Original bug: KT-25182

abstract class Expr<T>

class Sum<K>(val e: Expr<K>) : Expr<K?>() // note that argument is nullable (K?)

private fun <V> times(e: Expr<V>, element: V): Expr<V> = TODO()

private fun <S> foo(e: Expr<S>) {}

fun test(intExpression: Expr<Int>) {
    foo(Sum(times(intExpression, 42))) // type mismatch in NI, OK in old inference
}
