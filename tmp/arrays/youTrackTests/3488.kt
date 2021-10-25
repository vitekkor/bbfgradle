// Original bug: KT-30671

class GreenHero {
    val name by lazy { "Green Lantern" }

    init {
        showName()
    }

    private fun showName() = println(name)
}

fun main() {
    GreenHero()
}
