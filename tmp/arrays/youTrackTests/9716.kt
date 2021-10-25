// Original bug: KT-7415

class SwOperator<T>: Operator<List<T>, T>

interface Operator<R, T>

class Obs<Y> {
    fun <X> lift(lift: Operator<out X, in Y>) {}
}

fun foo(o: Obs<CharSequence>) {
    o.lift(SwOperator())
}
