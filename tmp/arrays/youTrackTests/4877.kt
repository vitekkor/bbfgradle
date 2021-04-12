// Original bug: KT-13399

open class MonopolyPlayer {
    var position: Int = 0
        internal set
}

// In another file
class MonopolyBoard {
    fun moveToPosition(player: MonopolyPlayer, position: Int) {
        player.position = position
    }
}
