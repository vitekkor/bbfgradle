// Original bug: KT-8948

inline fun foo(f: () -> Unit) {
    try {
        f()
    }
    finally {
    }
}

fun main(args: Array<String>) {
    foo {
        try {
            return
        } catch(e: Exception) {
            return
        }
    }
}
