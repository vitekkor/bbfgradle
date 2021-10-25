// Original bug: KT-3279

class Test {
    private val property: Int
        get() = 1 + 1

    fun print() {
        println(property)
    }
}

fun main(args: Array<String>) {
    val test = Test()
    test.print()
}
