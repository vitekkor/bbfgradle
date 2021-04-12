// Original bug: KT-29252

fun main() {
    A("").foo()
}

interface Foo {
    val p: String
}

class A(val prop: String) {
    fun foo() = Inner()

    inner class Inner {
        private val obj = 42.let {
            val o: Foo = object: Foo {
                override val p = prop
            }
            o
        }
    }
}
