// Original bug: KT-12126

package test.internal

import java.util.*

class A() {
    val list = ArrayList<Class<*>>()

    inner class B() {
        inline fun <reified T : Any> add() = list.add(T::class.java)
    }
}
