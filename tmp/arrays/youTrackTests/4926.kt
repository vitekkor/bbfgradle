// Original bug: KT-24308

data class Container(val name: String, private val items: List<Int>) : List<Int> by items

fun main(args: Array<String>) {
    val (name, items) = Container("Hello there", listOf(1, 2, 3))
    println(name)
    println(items)
}
