// Original bug: KT-20662

import Test9.foo

open class Base(val x: Any?)

object Test8 : Base(Test8::foo) {   // Error (reference in a bound callable reference receiver)
    fun foo() {}
}

object Test9 : Base(::foo) {        // Error (implicit reference in a bound callable reference receiver)
    fun foo() {}
}
