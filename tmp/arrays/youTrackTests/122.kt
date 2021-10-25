// Original bug: KT-45810

class TestService<T>(private val value: T) {

    fun interface CompletionHandler<R> {
        fun onFinished(result: Result<R>)
    }

    fun callMeBack(completion: CompletionHandler<T>) {
        val param = Result.success(value)
        completion.onFinished(param)
    }
}
