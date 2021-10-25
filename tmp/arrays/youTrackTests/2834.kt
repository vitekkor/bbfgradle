// Original bug: KT-40209

interface Observer<in T> {
    fun onSuccess(value: T)
}

interface Sink<out T> {
    fun subscribe(observer: Observer<T>)
}

inline fun <T> sinkUnsafe(crossinline onSubscribe: (Observer<T>) -> Unit): Sink<T> =
    object : Sink<T> {
        override fun subscribe(observer: Observer<T>) {
            onSubscribe(observer)
        }
    }

fun <T> sink(onSubscribe: (Observer<T>) -> Unit): Sink<T> =
    sinkUnsafe { observer ->
        val inner =
            object : Observer<T> {
                override fun onSuccess(value: T) {
                    foo {
                        observer.onSuccess(value)
                    }
                }

                private fun foo(block: () -> Unit) {
                    block()
                }
            }

        onSubscribe(inner)
    }
