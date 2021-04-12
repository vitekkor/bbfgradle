// Original bug: KT-16417

class A {
    companion object {
        // not required, just to make this runnable
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }

    var field = 0

    inline fun a(f: () -> Any): Any {
        try {
            val value = f()
            return value
        } finally {
            field--
        }
    }

    private inline fun b(rule: () -> Unit) {
        try {
            rule()
        } catch (fail: Throwable) {}
    }

    fun c(vararg functions: () -> Any): Any = a {
        for (function in functions) {
            b { return function() }
        }
        throw Throwable()
    }
}
