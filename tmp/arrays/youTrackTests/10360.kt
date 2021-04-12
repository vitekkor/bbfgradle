// Original bug: KT-6266

fun main(args: Array<String>) {
    println(listOf<Int>())
    println(ArrayList<Int>())

    println(mapOf<Int, String>())
    println(HashMap<Int, String>())

    println(setOf<Int>())
    println(HashSet<Int>())
}
