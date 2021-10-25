// Original bug: KT-40133

inline fun Foo.fooMethod(crossinline someLambda: () -> Unit) = apply {
    addBar(object : Bar() { override fun barMethod() = someLambda() })
}

class Foo {
    fun addBar(bar: Bar) {
        TODO("Not yet implemented")
    }
}

abstract class Bar {
    abstract fun barMethod()
}
