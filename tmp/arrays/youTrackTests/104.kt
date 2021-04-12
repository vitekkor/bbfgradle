// Original bug: KT-33992

inline fun foo(x: () -> Any) = Pair(x(), x())

fun main() {
    val (x, y) = foo {
        class C
        C()
    }
    val (a, b) = foo {
        object {}
    }

    println(x::class == y::class) // should print "true"
    println(a::class == b::class) // should print "true"
}
