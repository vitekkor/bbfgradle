// Original bug: KT-11634

interface A {
    fun foo(): Int
}

class AImpl() : A {
    override fun foo(): Int = 10
}

open class AFabric {
    open fun createA(): A = AImpl()
}

class AWrapperFabric : AFabric() {
    override fun createA(): A = 
        // This way everything is OK:
        // val a = super.createA()
        // object : A by a {
        object : A by super.createA() {
            override fun foo(): Int = 30
        }
}

fun main(args: Array<String>) {
    println("${AWrapperFabric().createA().foo()}")
}
