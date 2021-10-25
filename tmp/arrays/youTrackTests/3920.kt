// Original bug: KT-37573

object Singleton {
    private var count: Int = 0


    fun increment() { // "move is only supported for top level declarations", but members in kotlin object are static context so the message is nonsense.
    }
}
