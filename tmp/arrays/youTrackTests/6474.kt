// Original bug: KT-29684

package inspect.duplicate

class Temp {
    fun dup1a(p: Int) {
        when (p) {
            0 -> println("0")
            1 -> println("1")
            2 -> println("2")
            3 -> println("3")
            else -> println("9")
        }
    }

    fun dup1b(p: Int) {
        when (p) {
            0 -> println("0")
            1 -> println("1")
            2 -> println("2")
            3 -> println("3")
            else -> println("9")
        }
    }
}
