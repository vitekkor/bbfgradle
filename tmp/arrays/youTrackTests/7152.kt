// Original bug: KT-28234

package test

class M(size: Int) {
    val m = IntArray(size) { 0 }
}

inline operator fun M.get(a: Any, b: Any, ifn: () -> Int) =
        m[ifn()]

inline operator fun <reified T> M.set(a: T, b: Any, ifn: () -> Int, v: Int) {
    if (b !is T) throw AssertionError()
    m[ifn()] = v
}

fun main() {
    val m = M(4)
    m["a", "b", { 1 }] += 10
    println(m.m[1])
}
