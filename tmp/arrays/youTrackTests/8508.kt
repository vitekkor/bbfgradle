// Original bug: KT-18095

open class A {
    fun constructor() : Int {
        println("fun")
        return 42
    }
    init {
        println("init")
    }
}
class B : A()

fun main(args: Array<String>) {

    val b = B()
    val res = b.constructor()
    println(res)

}
