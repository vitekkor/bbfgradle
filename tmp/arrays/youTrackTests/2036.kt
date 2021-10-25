// Original bug: KT-8154

interface A<T> {
    fun foo()
}

interface B<T> : A<T>

class BImpl<T>(a: A<T>) : B<T>, A<T> by a

fun main(args: Array<String>) {
    BImpl<Int>(object : A<Int> { override fun foo() { print("1")} }).foo()
}
