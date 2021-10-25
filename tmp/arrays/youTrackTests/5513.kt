// Original bug: KT-32479

open class A {
    open fun getFoo(): String? = null
}

class B : A() {
    private val foo = ""
    override fun getFoo(): String? = super.getFoo() //this override can't be eliminated because it fixes name clash
}
