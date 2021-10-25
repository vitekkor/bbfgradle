// Original bug: KT-23015

sealed class Expr<out T> {
    object Nothing : Expr<kotlin.Nothing>()
    data class SomethingComplex<T>(val v: T): Expr<T>()
}


fun main(args: Array<String>) {
    val e: Expr<String> = Expr.Nothing // OK
}

