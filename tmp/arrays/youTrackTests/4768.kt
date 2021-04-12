// Original bug: KT-31653

inline fun a(f: () -> Any) =
    try {
        f()
    } finally {
        throw RuntimeException()
    }

fun b(vararg functions: () -> Any) = a {
    for (function in functions) {
        try {
            return function()
        } catch (fail: Throwable) {}
    }
}

fun main(args: Array<String>) {
    b({ println(1); 1 }, { println(2); 2 })
}
