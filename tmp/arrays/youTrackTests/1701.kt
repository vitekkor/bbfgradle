// Original bug: KT-36420

fun main() {
    println(N(2) in minOf(N(0), N(1))..N(3))
}

inline class N(val v: Int) : Comparable<N> {
    override fun compareTo(other: N) = v.compareTo(other.v)
}
