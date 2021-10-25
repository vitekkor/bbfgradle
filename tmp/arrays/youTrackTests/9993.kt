// Original bug: KT-8229

package test

fun test1() {
    var c: Base? = Derived()
    if (c !is Derived) return

    val newC: Derived? = Derived()
    if (newC != null) {
        c = newC
    }
    foo(c) // TYPE_MYSTMACH: Base? -> Derived
}

fun test2() {
    var c: Base? = Derived()
    if (c !is Derived) return

    val newC: Derived? = Derived()
    if (newC is Derived) {
        c = newC
    }
    foo(c) // OK
}

open class Base

open class Derived: Base()

fun foo(c: Derived) {  }
