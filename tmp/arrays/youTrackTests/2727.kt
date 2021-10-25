// Original bug: KT-15020

fun <T> foo(f: () -> T): T = f()

fun test(a: Number?) {
    val r1 = foo { a ?: throw Exception() } // r1 has type Number
    val r2 = foo { if (a is Int) a else throw Exception() } // r2 has type Int
}
