// Original bug: KT-2912

package toString

import kotlin.test.assertEquals

class A

fun A.toString() = "OK"

fun main(args: Array<String>) {
    val a = A()
    assertEquals(a.toString(), "$a")
}
