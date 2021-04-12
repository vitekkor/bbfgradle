// Original bug: KT-43245

fun main() {
    val numMap = mapOf(1 to 0, 2 to 1, 3 to 2, 4 to 5)
    val anothernumMap = mapOf(4 to 5, 1 to 0, 3 to 2, 2 to 1)

    println ("Yhe maps are equal: ${numMap == anothernumMap}")
}
