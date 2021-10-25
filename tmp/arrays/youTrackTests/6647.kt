// Original bug: KT-21085

package test

class Delegate(val value: String) {
    operator fun getValue(thisRef: Any?, kProperty: Any?) = value
}

fun box(): String {
    val x by Delegate("O")

    class Local(val y: String) {
        val fn = { x + y }
    }

    return Local("K").fn()
}

fun main(args: Array<String>) {
    println(box())
}
