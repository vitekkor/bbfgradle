// Original bug: KT-16417

class A {
    companion object {
        // not required, just to make this runnable
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }

    inline fun a(f: () -> Any): Any {
        try {
            return f()
        } finally {
            val x = 1
            val x2 = 2
        }
    }

    inline fun b(rule: () -> Unit) {
        try {
            rule()
        } catch (e: Exception) {

        }
    }

    fun c(function: String): Any = a {
        val z = "1"
        val z2 = "2"
        val z3 = "3"
        b { return function }
        throw Throwable(z + z2 + z3)
    }
}
