// Original bug: KT-45683

sealed class Failure{
    class Exception(val message: String): Failure()
    class HttpError(val code: Int): Failure()
}

sealed class Result<out T, out F: Failure>{
    class Success<T>(val data: T): Result<T, Nothing>()
    class Failed<F: Failure>(val failure: F): Result<Nothing, F>()
}
