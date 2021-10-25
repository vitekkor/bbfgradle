// Original bug: KT-15678

fun EmptyLambdaFactoryFunction() = {} // :o

class Test {
    val list = mutableListOf<() -> Unit>()

    init {
        val element = 1
        list += EmptyLambdaFactoryFunction()
        println(element)
    }
}

fun main(args: Array<String>) {
    Test()
}