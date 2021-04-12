// Original bug: KT-12734

fun main(args: Array<String>) {
    val a = 12

    val s = object: () -> Unit {
        override fun invoke() {
            println(a) // Breakpoint here works fine
        }
    }

    inlineF {
        s()
    }
}

inline fun <R> inlineF(block: () -> R): R = block()
