// Original bug: KT-20226

fun <T> foo(f: () -> T): T = f()

fun test(b: Boolean, i: Int) {
    // r1 has type Any
    val r1 = foo {
        if (b) return@foo 1
        1L // any expression of type Long, Byte, Short...
    }
}
