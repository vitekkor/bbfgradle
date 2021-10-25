// Original bug: KT-27511

sealed class Result<SuccessType, FailureType> {
    class Success<SuccessType, FailureType>(val value: SuccessType) : Result<SuccessType, FailureType>()
    class Failure<SuccessType, FailureType>(val error: FailureType) : Result<SuccessType, FailureType>()

    fun<T> flatMap(cb: (SuccessType) -> Result<T, FailureType>): Result<T, FailureType> =
        when (this) {
            is Success -> cb(value)
            is Failure -> Failure(error)
        }
}

private fun f(a: Result.Success<Int, String>): Result<Int, String> =
    a.flatMap<Int> {
        Result.Success(it)
    }
