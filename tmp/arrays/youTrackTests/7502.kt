// Original bug: KT-24170

import kotlin.reflect.full.*
import kotlin.reflect.jvm.*

open class A {
    val foo = "OK"
}

class B : A()

fun main(args: Array<String>) {
    // expected: class B
    // actual: class A
    println(B::foo.instanceParameter!!.type.jvmErasure)

    // expected: IllegalArgumentException thrown
    // actual: OK
    println(B::foo.call(A()))
}
