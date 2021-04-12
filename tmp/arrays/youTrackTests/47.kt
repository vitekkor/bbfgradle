// Original bug: KT-41620

interface A {
    fun a(): A
}
class A1 : A {
    override fun a() = test() ?: error("")
    @Suppress("UNCHECKED_CAST")
    private fun <V : A> test(): V? = this as? V
}
fun main() {
    val a: A = A1()
    println(a.a())
}
