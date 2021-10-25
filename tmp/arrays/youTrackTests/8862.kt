// Original bug: KT-18231

interface I {
    fun foo() {println("I")}
}
interface J : I {
    override fun foo() {println("J")}
}
open class A : I, J 
open class B : I by A()

fun main(args: Array<String>) {
    B().foo()
}
