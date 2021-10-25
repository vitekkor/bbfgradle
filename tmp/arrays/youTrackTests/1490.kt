// Original bug: KT-28637

open class BaseClass {
    open fun doSomething(a: String = "TestA", b: String = "TestB"): String {
        return a + b
    }
}

class KotlinImplementation : BaseClass() {
    @JvmOverloads
    override fun doSomething(a: String, b: String) = super.doSomething(a, b)
}
