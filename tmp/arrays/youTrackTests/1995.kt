// Original bug: KT-43257

interface Box<out E> {
    fun contains(element: @UnsafeVariance E): Boolean
}
class MyBox<E>(val containsPredicate: (E) -> Boolean) : Box<E> {
    override fun contains(element: E): Boolean = containsPredicate(element)
}
fun main() {
    val box: Box<Any?> = MyBox<String>(containsPredicate = { it.isNotEmpty() })
    println(box.contains(Any()))
}
