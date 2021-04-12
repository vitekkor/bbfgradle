// Original bug: KT-39263

abstract class ER {
    abstract val eRData: EffectRunnableData
    abstract fun run()
}

data class Player(val name: String, val age: Int)
class EffectRunnableData(player: Player)

val player = Player("John", 18)
val a = EffectRunnableData(player)

val runnable = object : ER() {
    override val eRData = a
    override fun run() {
        TODO("Not yet implemented")
    }
}
