// Original bug: KT-8552

interface T {
    fun foo() {}
}
class T1 : T
class T2 : T

open class A {
    open val x: T = T1()

    fun bar() {
        if (x is T1) {
            x.foo()   // <-- bad smart cast
        }
    }
}

class B : A() {
    var queried = false

    override val x: T
        get() = if (!queried) {
            queried = true
            T1()
        } else T2()
}

fun box(): String {
    B().bar()
    return "OK"
}
