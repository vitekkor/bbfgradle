// Original bug: KT-37333

class Random {
    fun nextInt(): Int {
        return 42
    }

    fun nextInt(bound: Int): Int {
        return 42
    }
}

fun main() {
    val random: (Int) -> Unit = { Random().nextInt(it) } // Convert lambda to reference
}
