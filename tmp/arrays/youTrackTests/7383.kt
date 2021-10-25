// Original bug: KT-27514

sealed class Either<A, B> {
    class Left<A, B>(val value: A) : Either<A, B>()
    class Right<A, B>(val value: B) : Either<A, B>()
}

fun f(): Either<String, Boolean> =
    Either.Left("abc")

fun g(e: Either<String, Boolean>): Int = when (e) {
    is Either.Left -> e.value.length
    is Either.Right -> if (e.value) 1 else 0
}

fun main(args: Array<String>) {
    println(g(f()))
}
