// Original bug: KT-33155

class Foo {
    fun foo() {
        val let: Any = "crashMe".let {
            object : Any() {
                override fun toString() = this@Foo.toString()
            }
        }
        let.toString()
    }
}

fun main() {
    Foo().foo()
}
