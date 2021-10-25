// Original bug: KT-7004

object Foo {
    inline fun call(a: Int) = a
    inline fun call(a: String) = 1
}

inline fun call(a: Int) = a
inline fun call(a: String) = 1

fun main(args : Array<String>) {
    println(call(2))
    println(call("2"))
    println(Foo.call(2))
    println(Foo.call(""))
}
