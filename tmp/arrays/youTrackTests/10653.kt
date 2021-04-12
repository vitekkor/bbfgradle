// Original bug: KT-3025

fun safeParseInt(s: String): Int? = try {
    Integer.parseInt(s)
} catch(ex: Exception) {
    null
}

fun main(args: Array<String>) {
    println(safeParseInt("3"))
}
