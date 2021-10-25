// Original bug: KT-30016

class SColl(private val s: String) : Collection<String> {
    override val size: Int
        get() = 1

    override fun contains(element: String): Boolean {
        return element.length == s.length && element == s
    }

    override fun containsAll(elements: Collection<String>): Boolean {
        return elements.all { it == s }
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun iterator(): Iterator<String> {
        return listOf(s).iterator()
    }
}
