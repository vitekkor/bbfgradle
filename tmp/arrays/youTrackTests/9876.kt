// Original bug: KT-10977

fun <T> test(x: T) {
    class T
    val y : T // OK, resolves to local class
}

class TestRedeclaration<T> {
    class T
    val x: T = null!! // OK, resolves to type parameter
}
