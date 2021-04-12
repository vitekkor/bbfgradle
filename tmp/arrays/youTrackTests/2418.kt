// Original bug: KT-41797

fun main() {
    val a = listOf(1, 2, 3)
    val b = listOf(11, 22, 33, 44, 55)
    for (element in a) {
        println(element) //now we're using `a` collection
    }
}
