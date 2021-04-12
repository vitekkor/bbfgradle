// Original bug: KT-19084

fun main(args: Array<String>) {
    println(B().lazyValue)
}

open class A {
    open val some: Int = 0
    val lazyValue by lazy { some }
}

class B: A() {
    override val some: Int

    init {
        println("Ready?")
        some = 42               // Stopping on breakpoint here affects the result
        println("Done!")
    }
}
