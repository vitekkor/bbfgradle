// Original bug: KT-18231

interface I {
    fun foo() {println("I")}
}
interface J : I {
    override fun foo() {println("J")}
}
open class A : I, J 
open class B : J, I

fun main(args: Array<String>) {
    A().foo() //J expected, I actually
    B().foo() //J expected, J actually
}
