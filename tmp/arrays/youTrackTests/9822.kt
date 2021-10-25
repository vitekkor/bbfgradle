// Original bug: KT-3633

class Cont<T>(val t: T)

class Supp<T>(val t: T)

fun <T, E : Cont<T>> doit(o: Any, e: E) {
    println("doit 1")
}

fun <T, E : Supp<T>> doit(o: Any, e: E) {
    println("doit 2")
}

fun tryit() {
    doit("hello", Cont("hello"))
}
