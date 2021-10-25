// Original bug: KT-17822

class Outer {
    data class Inner(val onSuccess: () -> Unit = {})

    val inner = Runnable {
        Inner().onSuccess()
    }
}

fun main(args: Array<String>) = Outer().inner.run()
