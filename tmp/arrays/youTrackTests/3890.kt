// Original bug: KT-16620

fun foo(map: MutableMap<String, Int>, str: String) = map.getOrPut(str) {
    return@getOrPut str.toInt() // delete getOrPut 
}

fun main() {
    val map = mutableMapOf<String, Int>()

    val a = foo(map, "5")
    println(a)
    println(map)
}
