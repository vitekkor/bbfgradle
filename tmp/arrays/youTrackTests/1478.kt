// Original bug: KT-26519

class Foo {
    companion object {
        @JvmStatic
        private inline fun <reified T> bar() {
        }
    }

    fun test() {
        bar<String>()
    }
}

fun main(args: Array<String>) {
    Foo().test()
}
