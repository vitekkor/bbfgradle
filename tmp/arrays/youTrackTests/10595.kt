// Original bug: KT-3733

data class Simple(val one: Int, val two: String)

fun simpleTest() {
    val simple = Simple(1, "Hello")

    val (one, two) = simple
    println("$one $two")
}


fun main(args: Array<String>) {
    simpleTest()
}
