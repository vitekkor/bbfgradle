// Original bug: KT-9143

inline fun foo(t1: Int, t2: Int) {}
inline fun bar(l: (Int) -> Unit): Int = null!!
fun use() {
    var x: Int?
    x = 5
    x.hashCode() // smart cast: Ok
    foo(bar { x = null }, x.hashCode()) // smart cast: ERROR! x is nullable here
}
