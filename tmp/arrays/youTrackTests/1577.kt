// Original bug: KT-44082

interface W
interface J
open class A
open class B: A(), W
open class E: A(), J


open class C {
    open fun foo(): A = B()
}

class D: C() {
    override fun foo() = if (true) B() else E()
}
val x = if (true) B() else E() // Inferred type of x is A
