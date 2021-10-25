// Original bug: KT-26859

inline class SnekDirection(private val direction: Int) {
    companion object {
        val Up = SnekDirection(0)
    }
}

fun testUnbox() : SnekDirection {
    val list = arrayListOf(SnekDirection.Up)
    return list[0]
}
