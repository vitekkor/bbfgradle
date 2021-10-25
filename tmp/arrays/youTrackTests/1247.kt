// Original bug: KT-33917

inline fun <T> f(g: (Int) -> T): Pair<T, T> =
    g(0) to g(1)

fun main(args: Array<String>) {
    val i = 1
    var (x, y) = f {
        object {
            fun bar() = i
        }
    }
    val z = x
    x = y
    y = z
    println(y.bar())
    println(x.bar())
}
