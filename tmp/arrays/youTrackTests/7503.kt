// Original bug: KT-18608

sealed class Either<out Err, out T>
data class Left<out Err>(val error: Err) : Either<Err, Nothing>()
data class Right<out T>(val value: T) : Either<Nothing, T>()

typealias Result<T> = Either<Throwable, T>
