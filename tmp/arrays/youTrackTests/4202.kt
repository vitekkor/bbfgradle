// Original bug: KT-20226

fun <T> foo(f: () -> T): T = f()

fun bar(a: Any) {} // (1)
fun bar(l: Long) {} // (2)

fun test(b: Boolean, i: Int) {
    val r1 = foo {
        if (b) return@foo 1
        1L
    }
    
    bar(r1)
}
