// Original bug: KT-18108

class Ex {
    fun Int.foo() = this + 1
    fun test() {
        val c = this.javaClass
        assert(Ex::class.java == c)
        val m = c.methods.first { it.name == "foo" }
        assert(9 == m.invoke(this, 8))
        c.getMethod("foo", Int::class.java);
    }
}
fun main(vararg args: String) {
    Ex().test()
}
