// Original bug: KT-43083

package a

class Box() {
    internal fun result(value: String = "OK"): String = value
}

// FILE: B.kt

fun box(): String {
    return a.Box().result()
}
