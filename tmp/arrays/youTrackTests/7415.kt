// Original bug: KT-27096

inline class Z1(val x: Int)
inline class Z2(val z: Z1)

fun peek(n: Int): Z2? = if (n < 0) null else Z2(Z1(n))

fun main(args: Array<String>) {
    println(peek(42))
}
