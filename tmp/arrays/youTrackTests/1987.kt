// Original bug: KT-20668

class GreenHero {
    init {
        showName()
    }

    val name by lazy { "Green Lantern" }

    private fun showName() = println(name)
}

fun main() {
    GreenHero()
}
