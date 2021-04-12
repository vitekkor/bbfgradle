// Original bug: KT-15020

inline fun <T> foo(f: () -> T): T = f()

fun test(a: Number?) {
    // r1 has type Int
    val r1 = foo { 
        a as Int
        a
    }
    
    // r2 has type Number
    val r2 = foo {
        if (a == null) return
        a
    }
}
