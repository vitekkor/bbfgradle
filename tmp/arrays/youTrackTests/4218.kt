// Original bug: KT-37376

fun main() {
    fun String.toBooleanOrNull(): Boolean? = false // Just a placeholder function

    val s = "false"
    val result = s.toBooleanOrNull() ?: s.toIntOrNull() != 0
    println(result) // âtrue"
}
