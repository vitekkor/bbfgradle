// Original bug: KT-7273

fun test1(): String {
    try {
        test {
            return@test1 ""
        }
    } finally {
    }
}


inline fun <R> test(s: () -> R): R {
    var b = false
    try {
        return s()
    } finally {
        !b
    }
}


