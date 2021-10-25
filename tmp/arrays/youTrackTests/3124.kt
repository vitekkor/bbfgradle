// Original bug: KT-34250

suspend fun <R> catch(f: suspend () -> Any): Either<Throwable, Any> =
    foo(f)

class Either<T, U>
suspend fun <L, R> foo(f: suspend () -> R): Either<L, R> = TODO()
