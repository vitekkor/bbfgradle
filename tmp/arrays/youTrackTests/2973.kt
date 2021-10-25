// Original bug: KT-38034

fun main() {
    val text: Map<String, Char> = "Kotlin".getFirstAndLast()

    val firstChar = text["first"]
    val lastChar = text["last"]

    println("First letter is $firstChar and $lastChar for second letter")
}

fun String.getFirstAndLast(): Map<String, Char> = mapOf(
    "last" to this.last(), "first" to this.first()
)
