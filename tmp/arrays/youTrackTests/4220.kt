// Original bug: KT-29242

fun main(args: Array<String>) {
    println(causesVerifyErrorSample())
}

fun causesVerifyErrorSample(): Sample<Boolean> = Sample
    // Calling Type with generic T
    .Success(true)
    .flatMap { Sample.Failure(RuntimeException()) }

sealed class Sample<out T> {
    companion object {
        fun <A> just(a: A): Sample<A> = Success(a)
        fun <A> raise(e: Throwable): Sample<A> = Failure(e)
    }

    inline fun <R> flatMap(f: (T) -> Sample<R>): Sample<R> =
        when (this) {
            // First clause been Type with generic Nothing
            is Failure -> this
            is Success -> f(this.value)
        }

    data class Failure(val exception: Throwable): Sample<Nothing>()
    data class Success<out T>(val value: T): Sample<T>()
}
