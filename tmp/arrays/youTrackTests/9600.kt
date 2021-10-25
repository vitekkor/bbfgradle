// Original bug: KT-11922

abstract class Abs<T> {
    fun a() {
    }

    abstract fun b(): T
}

class A {

    private fun foo() = object : Abs<String>() {
        override fun b(): String {
            throw UnsupportedOperationException()
        }

    }
}
