// Original bug: KT-30022

interface Interface {
    fun foo()
}

class Class: Interface {
    private val unused = 478

    override fun foo() { println("foo") }

    private fun blah() { println("blah") }
}

fun main() {
    Class()
}
