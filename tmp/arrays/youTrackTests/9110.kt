// Original bug: KT-16160

// MODULE: module2
import A.foo
import B.Companion.bar

object A {
    fun foo() = 1
    inline fun test() = foo() + this.foo() + A.foo()
}

class B {
    companion object {
        fun bar() = 2
        inline fun test() = bar() + this.bar() + B.bar()
    }
}

inline fun test2() = foo() + bar()
inline fun A.test2() = foo() + B.bar()
inline fun B.Companion.test2() = bar()
