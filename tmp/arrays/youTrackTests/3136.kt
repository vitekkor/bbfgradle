// Original bug: KT-38802

interface I<TValue : Any> {
    fun foo(value: TValue): Double
    fun bar()
}

class Impl<TValue : Any>(val function: (TValue) -> Double) : I<TValue>{
    override fun foo(value: TValue): Double {
        return function(value)
    }

    override fun bar() {
    }
}

inline class InlineClass(val value: Double)

fun main() {
    val i1 = Impl<InlineClass> { it.value }
    val i2 = object : I<InlineClass> by i1 {
        override fun bar() {
        }
    }
    i2.foo(InlineClass(1.0))
}
