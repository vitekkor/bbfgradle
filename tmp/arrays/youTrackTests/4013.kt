// Original bug: KT-7333

interface A<T, S : T>

fun main(args : Array<String>) {
    val a : A<*,*> = object : A<String, String> { }
    foo(a)
}

fun <T, S : T> foo(a : A<T, S>) { }
