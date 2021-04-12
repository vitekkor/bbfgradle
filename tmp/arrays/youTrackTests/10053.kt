// Original bug: KT-8928

class App {
    companion object {
        var s: String = "Fail"
            private set
    }

    fun init() {
        s = "OK"
    }
}

fun main(args: Array<String>) {
    App().init()
}
