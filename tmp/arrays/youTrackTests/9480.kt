// Original bug: KT-13160

package foo

import kotlin.collections.*

var global = ""

class A(val data: Array<Int>)

fun main(args: Array<String>) {
    val a = A(arrayOf(1, 2, 3))
    a.data[0] = listOf(1, 2, 3).fold(0) { a, b ->
        global += "$b;"
        a + b
    }
    println(a.data[0])
    println(global)
}
