// Original bug: KT-40580

package com.example.crash

interface Request<out T> {
    fun execute(): T
}

fun <T> Request<T>.execute(
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit
) {
    println("onError handler: $onError")
    val result = try {
        execute()
    } catch (t: Throwable) {
        onError(t)
        return
    }
    onSuccess(result)
}

abstract class AbstractSubmitter<T : Any, R : Any> {

    private val lock = Any()

    fun submit(value: T) {
        synchronized(lock) {
            val request = newRequest(value)
            request.execute(
                onSuccess = { result -> 
                    onSuccess(result)
                },
                onError = { t ->
                    onError(t)
                }
            )
        }
    }

    protected abstract fun newRequest(value: T): Request<R>

    protected open fun onSuccess(result: R) {
        println("onSuccess: $result")
    }

    private fun onError(t: Throwable) {
        println("onError: $t")
    }
}

class IntSubmitter : AbstractSubmitter<Int, String>() {

    override fun newRequest(value: Int): Request<String> = object : Request<String> {
        override fun execute(): String {
            TODO("Unimportant")
        }
    }
}

fun main() {
    val submitter = IntSubmitter()
    submitter.submit(5)
}
