// Original bug: KT-25762

object Logger {
    fun log(x: String) {
        TODO()
    }
}

class Foo {

    fun test(x: (String) -> Unit = {}, y: (Int) -> Unit = {}) {
        TODO()
    }

    fun bar() {
        test(x = Logger::log)
    }

}
