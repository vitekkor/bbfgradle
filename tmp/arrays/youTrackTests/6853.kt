// Original bug: KT-10001

fun foo(p: Pair<Int?, Int>): Int {
    // Surprisingly IDE show nothing here, but compiler reports "unnecessary assertion"
    // If removed, smart cast impossible appears instead because p.first is a part of public API
    if (p.first != null) return p.first!!
    return p.second
}
