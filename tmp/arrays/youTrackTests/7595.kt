// Original bug: KT-26223

fun main(args: Array<String>) {
    val a = listOf(10u, 20u, 30u)
    a.forEachIndexed { index, uInt ->
        println(index)
        println(uInt)
    }
}
