// Original bug: KT-2974

class A(var p:String = "hi")

abstract class B{
    fun f1(): A? = A()
    fun f2(): A = A()
}

object C {
    val b:B = object: B(){}

    public fun f(p:String) {
        b.f1()!!.p = p   // OK!
        b.f2().p = p   // OK!
        b.f2()!!.p = p   // OK!

        b.f1()?.p = p   // ERROR!
        b.f2()?.p = p   // ERROR AGAIN !

    }
}
