// Original bug: KT-19367


class Service {
    private val disposable = ""

    fun doSomeWork() {
        println(disposable.length)
        doOtherWork()
    }

    companion object {
        var disposable = 5

        fun doOtherWork() {
            println(disposable)
        }
    }
}

fun main(args: Array<String>) {
    Service().doSomeWork()
}
