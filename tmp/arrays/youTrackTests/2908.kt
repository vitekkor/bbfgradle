// Original bug: KT-40071

open class Base
open class Derived : Base()

fun test() {
    val array: Array<Base> = arrayOf(Base())
    array[0] = Derived() as Base // expected [USELESS_CAST] No cast needed
}
