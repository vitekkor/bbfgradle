// Original bug: KT-15020

inline fun <T> foo(f: () -> T): T = f()

fun bar(n: Number?) {} // (1)
fun bar(n: Int) {} // (2)

fun test(a: Number?) {
    val r1 = foo {
        if (a !is Int) return
        a
    }

    bar(r1)
}
