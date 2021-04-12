// Original bug: KT-41915

package test

open class RemoveStringNImpl {
    fun remove(s: String?): Boolean {
        print("[RemoveStringNImpl.remove(String?)]")
        return false
    }
}

class S1 : Set<String>, RemoveStringNImpl() {
    override val size: Int get() = TODO()
    override fun contains(element: String): Boolean = TODO()
    override fun containsAll(elements: Collection<String>): Boolean = TODO()
    override fun isEmpty(): Boolean = TODO()
    override fun iterator(): Iterator<String> = TODO()
}

class S2 : Set<String> {
    override val size: Int get() = TODO()
    override fun contains(element: String): Boolean = TODO()
    override fun containsAll(elements: Collection<String>): Boolean = TODO()
    override fun isEmpty(): Boolean = TODO()
    override fun iterator(): Iterator<String> = TODO()

    fun remove(s: String?): Boolean {
        print("[S2.remove(String?)]")
        return false
    }
}

fun testRemove(s: Set<String>) {
    print(s.javaClass.simpleName)
    try {
        (s as java.util.Set<String>).remove("")
        println(" -- OK")
    } catch (e: Throwable) {
        println(" -- ${e.javaClass.simpleName}: ${e.message}")
    }
}

fun main() {
    testRemove(S1())
    testRemove(S2())
}
