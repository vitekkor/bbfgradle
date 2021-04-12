// Original bug: KT-43210

class Foo {
    fun bar() = Str

    companion object {
        val Str = "zzz${foo()}"

        private fun foo() = ConstStr

        const val ConstStr = "42"
    }
}

fun main() = println(Foo().bar())
