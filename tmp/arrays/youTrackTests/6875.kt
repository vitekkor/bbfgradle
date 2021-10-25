// Original bug: KT-29339

package test

import test.CallableRefExample4.foo
import kotlin.reflect.KFunction

abstract class Base(val z: KFunction<Unit>)

object CallableRefExample4 : Base(::foo) {
    fun foo() { println(this) }
}

fun main() {
    CallableRefExample4.z.call()
}
