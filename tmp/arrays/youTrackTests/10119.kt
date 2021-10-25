// Original bug: KT-8923

fun main(args: Array<String>) {
    val letters: Set<String> = setOf("a", "b", "c")

    val default = "d"
    fun countLetter(): String? = "m"

    val hasLetter: Boolean = countLetter() ?: default in letters
}
