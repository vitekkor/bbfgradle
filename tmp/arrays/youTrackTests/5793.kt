// Original bug: KT-19271

/* inline */ fun <T> b(block: () -> T) = block()

fun Any.member() = b {
    // Evaluate 'this.javaClass' prints actual class for generated lambda `class Kt19271Kt$member$1`
    println(this.javaClass) // always prints 'class java.lang.Integer' as expected
}

fun main(args: Array<String>) {
    12.member()
}
