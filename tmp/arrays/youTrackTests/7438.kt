// Original bug: KT-27318

package test

interface IFoo {
    fun foo(): String
}

inline class InlineFooImpl(val s: String): IFoo {
    override fun foo(): String = s
}

class Test : IFoo by InlineFooImpl("abc")
