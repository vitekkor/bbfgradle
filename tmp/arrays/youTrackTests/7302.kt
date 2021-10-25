// Original bug: KT-14426

object Wrapper {
    inline operator fun invoke(action: () -> Unit): Unit? {
        println("start wrap")
        try {
            val result = action()
            println("end wrap")
            return result
        } catch (ex: Throwable) {
            println("fail!") //breakpoint
            return null
        }
    }
}

fun main(args: Array<String>) {
    val wrapper = Wrapper
    wrapper {
        println("action")
        throw RuntimeException("Ex")
    }
}
