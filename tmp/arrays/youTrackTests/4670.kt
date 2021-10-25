// Original bug: KT-28603

fun main() {
    val myClass = MyClass()
    //Breakpoint!
    myClass.f1 { println("foo") }
            .f2 { println("bar") }
}

class MyClass {
    inline fun f1(f1Param: () -> Unit): MyClass {
        f1Param()
        return this
    }

    inline fun f2(f1Param: () -> Unit): MyClass {
        f1Param()
        return this
    }
}
