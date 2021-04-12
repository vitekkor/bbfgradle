// Original bug: KT-13490

import kotlin.reflect.KProperty

class A(val x: String)

fun main(args: Array<String>) {
    val p1 = A::x
    val p2 = A::class.members.single { it.name == "x" } as KProperty
    println(p1.getter == p2.getter)  // expected "true", actual "false"
}
