// Original bug: KT-21313

fun main(args: Array<String>) {
    val zs = shortArrayOf(3, 5, 8)
    val xs = intArrayOf(3, 5, 8)
    val ys = longArrayOf(3, 5, 8)
    print(xs)
    print(ys)
    return
}
