// Original bug: KT-8462

sealed class Either<out L, out R> {
    data class Left<L> (val value: L): Either<L, Nothing>()
    data class Right<R>(val value: R): Either<Nothing, R>()
}

inline fun <L, R, L2> Either<L, R>.mapLeft(func: (L) -> L2): Either<L2, R> = when(this) {
    is Either.Left  -> Either.Left(func(value))
    is Either.Right -> this
}

inline fun <L, R, R2> Either<L, R>.flatMap(func: (R) -> Either<L, R2>): Either<L, R2> = when(this) {
    is Either.Left  -> this
    is Either.Right -> func(value)
}
