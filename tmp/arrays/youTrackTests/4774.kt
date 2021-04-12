// Original bug: KT-34641

fun main() {
    inlineFunWithLamda {
        val lambdaVal = 0
        println() //breakpoint here
    }
}

inline fun inlineFunWithLamda(lambda: () -> Unit) {
    val inlineVal = 1
    println() //breakpoint here
    lambda()
}
