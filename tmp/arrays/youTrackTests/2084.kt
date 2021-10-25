// Original bug: KT-41915

package test

class G<T>

class S1 : Set<G<String>> {
    override val size: Int get() = TODO()
    override fun contains(element: G<String>): Boolean = TODO()
    override fun containsAll(elements: Collection<G<String>>): Boolean = TODO()
    override fun isEmpty(): Boolean = TODO()
    override fun iterator(): Iterator<G<String>> = TODO()

    fun remove(g: G<Int>) = false
}

open class RemoveGInt {
    fun remove(g: G<Int>) = false
}

class S2 : Set<G<String>>, RemoveGInt() {
    override val size: Int get() = TODO()
    override fun contains(element: G<String>): Boolean = TODO()
    override fun containsAll(elements: Collection<G<String>>): Boolean = TODO()
    override fun isEmpty(): Boolean = TODO()
    override fun iterator(): Iterator<G<String>> = TODO()
}

val g = G<String>()

fun testRemove(s: Set<G<String>>) {
    print(s.javaClass.simpleName)
    try {
        (s as java.util.Set<G<String>>).remove(g)
        println(" -- OK")
    } catch (e: Throwable) {
        println(" -- ${e.javaClass.simpleName}: ${e.message}")
    }
}

fun main() {
    testRemove(S1())
    testRemove(S2())
}
