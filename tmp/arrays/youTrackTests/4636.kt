// Original bug: KT-35348

import ImportBug.Face  // incorrect
import ImportBug.Suit.SPADES

class ImportBug {
    enum class Suit() {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    enum class Face {
        `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, JACK, QUEEN, KING, ACE
    }

    fun method() {
        val tenOfSpades = Pair(Face.`10`, SPADES) // attempt to add import for Face.`10`
    }
}
