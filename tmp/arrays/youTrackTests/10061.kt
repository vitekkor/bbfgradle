// Original bug: KT-8942

package q

interface I {
    fun <T> foo() where T : Cloneable, T : Comparable<T>
}

class C : I {
    override fun <T> foo() where T : Cloneable, T : Comparable<T> {
    }
}
