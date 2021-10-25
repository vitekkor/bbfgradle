// Original bug: KT-1700

open class A {
    public open fun self() : A = this
}

class B : A() {
    override fun self() = this  //no error or warning, 'B' is inferred

    fun test() {
        val s : B = self()
    }
}
