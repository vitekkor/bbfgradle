// Original bug: KT-31653

class A {
    inline fun a(f: () -> Any): Any {
        try {
            val value = 1
            return f()
        } finally {
            var z = 1
        }
    }

    fun c(vararg functions: () -> Any): Any = a {
        for (function in functions) {
            try {
                return function()
            } catch (fail: Throwable) {
            }
        }
        throw RuntimeException()
    }
}


fun main(args: Array<String>) {
    A().c({ println(1); 1 }, { println(2); 2 })
}
