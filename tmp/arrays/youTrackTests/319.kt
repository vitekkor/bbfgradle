// Original bug: KT-45259

fun interface ResultHandler<T> {
  fun onResult(result: Result<T>)
}

fun doSmth(resultHandler: ResultHandler<Boolean>) {
    resultHandler.onResult(Result.success(true))
}

fun main() {
    //Throws ClassCastException
    doSmth { result ->
        result.isSuccess
        println("Hello")
    }
    //Works as expected
    doSmth(object : ResultHandler<Boolean> {
        override fun onResult(result: Result<Boolean>) {
            result.isSuccess
        	println("Hello")
        }
    })
}
