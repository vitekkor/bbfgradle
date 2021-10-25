// Original bug: KT-26858

inline class Direction(private val direction: Int) {
    fun dx() = dx[direction]
    fun dy() = dy[direction]

    companion object {
        private val dx = intArrayOf(0, 1, 0, -1)
        private val dy = intArrayOf(-1, 0, 1, 0)
    }
}

fun main(args: Array<String>) {
    Direction(42).dx()
}
