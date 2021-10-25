// Original bug: KT-7653

package bug

open class A {
    val foo: Int = 0
    var bar: Int = 0
}

class B : A() {
    fun test() {
        super.foo
        super.bar
        super.bar = 2
    }
 }

fun main(args: Array<String>) {
    B().test()
}
