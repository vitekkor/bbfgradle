// Original bug: KT-28232

package test

class M(size: Int) {
    val m = IntArray(size) { 0 }
}

inline operator fun M.get(ifn: () -> Int) = m[ifn()]

inline operator fun M.set(ifn: () -> Int, v: Int) {
    m[ifn()] = v
}

fun main() {
    val m = M(4)
    m[{ 1 }] = 10 // (*)
    println(m.m[1])
}
