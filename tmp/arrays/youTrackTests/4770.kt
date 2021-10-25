// Original bug: KT-31653

fun c(vararg functions: () -> Any): Any =
    try {
        for (function in functions) {
            try {
                return function()
            } catch (fail: Throwable) {
            }
        }
    } finally {
        throw RuntimeException()
    }
