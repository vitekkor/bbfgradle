// Original bug: KT-22608

package foo
import foo.A.Companion.CONSTANT

class A {
    companion object {
        val CONSTANT = "HELLO"
    }
}

open class C(s: String)
class B(p2: String): C("test ${CONSTANT}") // Move B to a new file
