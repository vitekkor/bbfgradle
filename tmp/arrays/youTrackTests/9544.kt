// Original bug: KT-12698

open class Base<T> {
    inner class Inner(val x: Int)
}

class Derived : Base<Int>() {
    val test1: Inner = Inner(42)            // WRONG_NUMBER_OF_TYPE_ARGUMENTS
    val test2: Base<Int>.Inner = Inner(42)  // Ok    
}
