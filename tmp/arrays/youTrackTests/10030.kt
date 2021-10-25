// Original bug: KT-9428

open class Player 
open class SlashPlayer : Player() 

public abstract class Game<T: Player> {
    abstract fun getPlayer(z: String, create: Boolean = true) : T?
}

class SimpleGame: Game<SlashPlayer>() {
    override fun getPlayer(z: String, create: Boolean): SlashPlayer? {
        return SlashPlayer()
    }
}

fun main(args: Array<String>) {
    SimpleGame().getPlayer("123")
}
