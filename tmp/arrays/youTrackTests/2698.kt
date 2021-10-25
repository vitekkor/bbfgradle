// Original bug: KT-19498

package p2

import p2.A.Companion.CONSTANT

class A {
    companion object {
        val CONSTANT = "HELLO"
    }
}

open class C(s: String)
class B(p2: String) :C("test ${CONSTANT}") // Move B to new file
