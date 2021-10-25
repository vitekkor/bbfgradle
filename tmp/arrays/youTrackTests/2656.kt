// Original bug: KT-39263

// usage:

fun main() {

    val preEffectRunnableData = EffectRunnableData(Player("John", 18))
    useData(preEffectRunnableData)

    object : EffectRunnable() {

        override val effectRunnableData = preEffectRunnableData
        override fun run() {
            useData(effectRunnableData) // the bug appears if you use the value inside of the object
        }

    }

}

fun useData(effectRunnableData: EffectRunnableData) = println(effectRunnableData.player.toString())

// classes:

abstract class EffectRunnable {
    abstract val effectRunnableData: EffectRunnableData
    abstract fun run()
}

class EffectRunnableData(val player: Player)

// example player class:
data class Player(val name: String, val age: Int)
