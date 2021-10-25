// Original bug: KT-21968

interface A {
    fun f(cause: Int? = null): Boolean
}

open class B : A {  
   override fun f(cause: Int?): Boolean = true
}

class D : B(), A

fun main(args: Array<String>) {
    println(D().f())
}
