// Original bug: KT-40071

open class Base
open class Derived : Base()

@JvmField
var property: Base = Base()

fun test(array: Array<Base>) {
    property = Derived() as Derived
    property = Derived() as Base
    array[0] = Derived() as Derived
    array[0] = Derived() as Base // expected [USELESS_CAST] No cast needed

    var captured = Base() as Base;
    {
        captured = Derived() as Derived
        captured = Derived() as Base
    }()
}
