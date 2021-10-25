// Original bug: KT-19827

fun main(args: Array<String>) {
    val env = Pair(0, 0)
    fun point(z: Int) = env.first
    fun point() = point(0)

    arrayOf(10).forEach {
        point()
        point(0)
    }
}
