// Original bug: KT-3633

class Cont<T>(val t: T)

class Supp<T>(val t: T)

fun <T> doit(o: Any, e: Cont<T>) {
    println("doit 1")
}

fun <T> doit(o: Any, e: Supp<T>) {
    println("doit 2")
}

fun tryit() {
    doit("", Cont(""))
}
