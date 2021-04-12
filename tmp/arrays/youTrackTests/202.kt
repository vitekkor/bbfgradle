// Original bug: KT-20662

package test

abstract class Base(val z: () -> Unit)

object Example_3_1 : Base(Example_3_1::foo) {
    fun foo() {}
}

fun main() {
    Example_3_1.z()
}
