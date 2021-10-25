// Original bug: KT-45431

package test

import kotlin.reflect.KProperty

class DP {
    operator fun provideDelegate(t: Any?, kp: KProperty<*>) =
        lazy { "OK" }
}

class H {
    companion object {
        val property: String by DP()
    }
}

fun main() {
    println(H.property)
}
