// Original bug: KT-20662

package test

abstract class Base(val z: () -> Unit)

object Example_3_2 : Base(Example_3_2::foo)

fun Example_3_2.foo() {}

fun main() {
    Example_3_2.z()
}
