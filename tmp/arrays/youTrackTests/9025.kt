// Original bug: KT-17085

fun main(args: Array<String>) {
    foo<List<Int>>()
}

fun <T> foo(token: Any = object : TypeToken<T> {}) {
    token::class.java.genericInterfaces[0].toString() // NPE from Java reflection
}

interface TypeToken<T>
