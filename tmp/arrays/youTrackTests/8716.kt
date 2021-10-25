// Original bug: KT-19367

package test

class Service {
    private val disposable = ""

    fun doSomeWork() {
        apply {
            println(disposable.length)
        }
    }

    companion object {
        val disposable = 5
    }
}

fun main(args: Array<String>) {
    Service().doSomeWork()
}
