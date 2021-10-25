// Original bug: KT-22839


inline fun f(noinline p: () -> Unit) {
    p()
}

inline fun g(p: () -> Unit) {
    p()
}

fun test1f() = f { println("I'm not inlined") }
fun test1g() = g { println("Indeed, inlined I am") }

val v = { print("Guess what happens") }

fun test2f() = f(v)
fun test2g() = g(v)
