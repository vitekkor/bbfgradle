// Original bug: KT-31198

class KotlinBug {
    interface Inf {
        fun print(string: String)
    }

    class Printer(val name: String) : Inf {
        override fun print(string: String) {
            println("$name:[$string]")
        }
    }

    companion object {
        fun <I> function(arg: I, vararg func: (I) -> Unit) {
            func.forEach { it(arg) }
            println()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val a = Printer("A")
            val b = Printer("B")
            val arrayFromClass = listOf(a, b).map { it::print }.toTypedArray()

            val x = fun(string: String) { println("X:[$string]") }
            val y = fun(string: String) { println("Y:[$string]") }
            val arrayFromAnonymous = listOf(x, y).toTypedArray()

            function("Single Class", a::print)
            function("Anyone From Class Array", arrayFromClass[0])
            function("Anyone From Class Array", arrayFromClass[1])
            function("Anonymous Lambda Array", *arrayFromAnonymous)
            function("Class Array", *arrayFromClass)
        }
    }
}
