// Original bug: KT-20662

package test

abstract class Base(val z: () -> Unit)

object Example_3_3 : Base(Example_3_3::foo)

private fun Example_3_3.foo() {
    println(this)
}

fun main() {
    Example_3_3.z()
}
