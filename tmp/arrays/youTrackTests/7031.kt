// Original bug: KT-15956

package foo

import kotlin.reflect.full.primaryConstructor

class A(val x: Int, val y: Int)

inline fun <reified T : Any> f() {
    for (p in T::class.primaryConstructor!!.parameters.sortedBy { it.index }) {
        println(p)
    }
}
