// Original bug: KT-34507

class Queue<T>(override val size: Int) : Collection<T> {
    override fun contains(element: T): Boolean = TODO()

    override fun containsAll(elements: Collection<T>): Boolean = TODO()

    override fun isEmpty(): Boolean = TODO()

    override fun iterator(): Iterator<T> = TODO()

    fun remove(v: T): Any = v as Any // Return-type is Any, it's important
}

fun box(): String {
    (Queue<String>(1) as java.util.Collection<String>).remove("") // throws CCE

    return "OK"
}

fun main() {
    println(box())
}
