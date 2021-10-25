// Original bug: KT-31653

class A {
    var field = 0

    inline fun a(f: () -> Any): Any {
        try {
            val value = f()
            return value
        } finally {
            field--
        }
    }

    fun c(vararg functions: () -> Any): Any = a {
        for (function in functions) {
            try { return function() } catch (fail: Throwable) { }
        }
        throw RuntimeException()
    }
}
