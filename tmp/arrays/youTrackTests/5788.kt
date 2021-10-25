// Original bug: KT-25441

class MyClass {
    private var privateVar: Int = 0
    lateinit var lateinitVar: List<Int>

    init {
        lateinitVar = listOf()
    }
}

fun main(args: Array<String>) {
    val c = MyClass()
    println()
}
