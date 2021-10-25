// Original bug: KT-24643

class Delegate<T : Any> {
    private var v: T? = null

    operator fun getValue(thisRef: Any?, kp: Any?): T = v!!
    operator fun setValue(thisRef: Any?, kp: Any?, newValue: T) { v = newValue }
}

var <T : Any> List<T>.foo by Delegate<T>()

fun useString(s: String) {
    println(s)
}

fun main() {
    listOf(1).foo = 42
    useString(listOf("A").foo) // CCE
}
