// Original bug: KT-4285

open class A {
    open fun foo(x: Int = 0) {}
}

class B: A() {
    tailrec override fun foo(x: Int) {
        foo() // no warning, should be a specialized version of NON_TAIL_RECURSIVE_CALL
    }
}
