// Original bug: KT-41931

abstract class Base {
    abstract val originalVal: String
}

fun Base.getReversedValue() = originalVal.reversed()

abstract class Derived : Base() {
    val reversedVal: String = getReversedValue()
}

object DerivedImpl: Derived() {
    override val originalVal = "abc"
}

fun main() {
    println(DerivedImpl.reversedVal)
}
