// Original bug: KT-35595

class AutoCompleteBug {
    enum class Card {
        ACE_OF_SPADES, `10_OF_SPADES`,`2_OF_DIAMONDS`
    }

    fun method() {
        val tenOfSpades = Card.`10_OF_SPADES`
    }
}
