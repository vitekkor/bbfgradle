// Original bug: KT-37194

interface Base {
    val message: String
    fun print() {
        println(message)
    }
}

class BaseImpl : Base {
    override val message = "BaseImpl"
}

class DerivedWithOverride(b: Base) : Base by b {
    override val message = "DerivedWithOverride"
    override fun print() { // Redundant overriding method
        super.print()
    }
}

class DerivedNoOverride(b: Base) : Base by b {
    override val message = "DerivedNoOverride"
}

fun main() {
    val base = BaseImpl()
    val derived1 = DerivedWithOverride(base)
    derived1.print() // prints "DerivedWithOverride"

    val derived2 = DerivedNoOverride(base)
    derived2.print() // prints "BaseImpl"
}
