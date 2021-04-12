// Original bug: KT-30671

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
