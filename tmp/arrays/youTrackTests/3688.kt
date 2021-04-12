// Original bug: KT-31585

inline class Inline(val value: String)

interface Base<T> {
    fun get(): T
}

class BaseImpl<T>(private val element: T) : Base<T> {
    override fun get(): T {
        return element
    }
}

class Derived(
    val foo: Base<Inline>
) : Base<Inline> by foo

fun main() {
    val a = Derived(BaseImpl(Inline("value1")))
    a.get() // CCE : class b.Inline cannot be cast to class java.lang.String (b.Inline is in unnamed module of loader 'app'; java.lang.String is in module java.base of loader 'bootstrap')
}
