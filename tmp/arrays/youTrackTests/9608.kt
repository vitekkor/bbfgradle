// Original bug: KT-11997

class Widget {
    private interface DoStuff {
        fun doStuff()
    }

    private inline fun DoStuff(crossinline f: () -> Unit) = // (*)
            object : DoStuff {
                override fun doStuff() {
                    f()
                }
            }

    private val HELLO = DoStuff { println("Hello, world!") }

    fun doStuff() {
        HELLO.doStuff()
    }
}


fun main(args: Array<String>) {
    Widget().doStuff()
}
