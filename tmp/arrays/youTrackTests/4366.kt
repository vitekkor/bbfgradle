// Original bug: KT-37054

interface Child<T>
class Parent<T> : Child<T>

fun <K> materializeChild(): Child<K> = TODO()

fun test(p: Parent<String>) {
    val a = if (true) materializeChild() else p // Error: Child<String> is not subtype of Parent<String>
}
