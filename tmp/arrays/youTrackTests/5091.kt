// Original bug: KT-18602

abstract class A {
    /**
     * (open) Here's something very important about this method.
     */
    open fun test() {

    }

    /**
     * (abstract) Here's something very important about this method.
     */
    abstract fun test2()

}

class B: A() {
    override fun test() {

    }

    override fun test2() {

    }
}
