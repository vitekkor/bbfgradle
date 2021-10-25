// Original bug: KT-29996

// Base class compiled with -jvm-target 1.8
open class Base {
    inline fun inlineFunBase(p: () -> Unit) {
        p()
    }
}

// Derived class compiled with -jvm-target 1.6
class Derived : Base() {
    fun test() {
        inlineFunBase {} // no error, missed diagnostic
    }
}
