// Original bug: KT-8550

enum class Deck(val color: String) {
    SPADES("black"),
    CLUBS("black"),
    DIAMONDS("red"),
    HEARTS("red")
}

fun test() {
    println(Deck.SPADES.color) // "black"
    println(Deck.HEARTS.color) // "red"
}
