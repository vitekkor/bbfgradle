// Original bug: KT-22608

package foo

class A {
    companion object {
        val CONSTANT = "Hello !!!"
    }
}

open class C(val s: String)
class B : C("test ${A.CONSTANT}") // Move B to a new file
