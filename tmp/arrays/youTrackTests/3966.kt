// Original bug: KT-20867

class Foo<in T> {
    fun foo(t: T) {}
}

fun <T> bar(action: Foo<T>.() -> Unit) {}


class SuspendFoo<in T> {
    suspend fun foo(t: T) {}  
}

fun <T> suspendBar(action: suspend SuspendFoo<T>.() -> Unit) {}

