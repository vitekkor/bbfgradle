// Original bug: KT-44863

class MySet(val impl: Set<Int>): Set<Int> by impl

abstract class MyMap<T>: Map<Int, T>

class MyMapImpl<T>(
    override val keys: MySet,
    val impl: Map<Int, T>
) : MyMap<T>(), Map<Int, T> by impl

fun main() {
    MyMapImpl(MySet(setOf(1)), mapOf(1 to 1)).keys
}
