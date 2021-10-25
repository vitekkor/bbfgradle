// Original bug: KT-40133

package com.snap.test


inline fun anInlineFunction(crossinline crossInlineLamba: () -> Unit) {

    val foo = Foo()
    // This works
    foo.barMethod { crossInlineLamba() }

    // This doesn't
    foo.apply {
        barMethod { crossInlineLamba() }
    }
}

class Foo {
    fun barMethod(aLambda: () -> Unit) {}
}
