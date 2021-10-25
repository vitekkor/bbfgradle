// Original bug: KT-24217

val double = { i: Int -> i * 2 }
infix fun <A, I, R> ((A) -> I).then(f: (I) -> R) = { a: A -> f(this(a)) }
fun <A, I, R> ((I)->R).after(f: (A)->I) = { a: A -> this(f(a)) }
fun <T> id(x: T): T = x

fun main(args: Array<String>) {
    val f = double then ::id
    val f2 = double.after(::id)
}
