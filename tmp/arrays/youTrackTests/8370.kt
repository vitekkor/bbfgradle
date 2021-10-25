// Original bug: KT-17473

open class A {
    companion object B {
        fun f1() {
            println("A.B.f1")
        }
        fun f2() {
            println("A.B.f2")
        }
        fun f3() {
            println("A.B.f3")
        }
   }
}

open class E {
    fun f2() {
        println("E.f2")
    }
}

class C : A() {
    companion object D : E() {
        fun f1() {
            println("C.D.f1")
        }
    }
    fun foo() {
        f1()
        f2()
        f3()
    }
}


fun main(args: Array<String>) {
    C().foo()
}
