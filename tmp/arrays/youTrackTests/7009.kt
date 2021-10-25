// Original bug: KT-29125

class test {
    constructor() {
        println("test constructor")
    }

    companion object {
        operator fun invoke() = println("test companion invocation")
    }
}

fun main(args: Array<String>) {
    test() // Prints: "test constructor"

    val test = object {
        operator fun invoke() = println("test invocation")
    }

    test() // Prints: "test invocation"

    fun test() = println("test function")

    test() // Prints: "test function"
}
