// Original bug: KT-24143

class Data<out T>(val value: T?)

fun <T> exampleMap(cs: List<Data<T>>): List<T?> {
    return cs.map { it.value }
}

fun <T> exampleMapNotNull(cs: List<Data<T>>): List<T> {
    return cs.mapNotNull { it.value } // error here
}

fun main(args: Array<String>) {
    val list: List<Data<String>> = listOf(Data("a"), Data(null), Data("b"))
    println(exampleMap(list))
    println(exampleMapNotNull(list))
}
