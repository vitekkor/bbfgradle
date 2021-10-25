// Original bug: KT-32228

interface Matcher<T> {
    fun test(value: T)
}

inline class Foo(val s: Any?) {
    val isString get() = s is String
}

class Ok : Matcher<Foo> {
    override fun test(value: Foo) {
        println(value.isString)
    }
}

fun fail(): Matcher<Foo> = object : Matcher<Foo> {
    override fun test(value: Foo) {
        println(value.isString)
    }
}

fun main() {
    val a = Foo("")
    Ok().test(a) // true
    fail().test(a) // false
}
