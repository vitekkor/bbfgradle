// Original bug: KT-21883

sealed class Result {
  object Success : Result()
  data class Failure(val exception: RuntimeException) : Result()

  interface Callback<T> {
    fun onSuccess(success: Result.Success) : T
    fun onFailure(failure: Result.Failure): T
  }
}
fun <T> Result.invoke(callback: Result.Callback<T>) = when (this) {
  is Result.Success -> callback.onSuccess(this)
  is Result.Failure -> callback.onFailure(this)
}

