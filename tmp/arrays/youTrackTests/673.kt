// Original bug: KT-42933

class Delegate {
    operator fun getValue(t: Any?, p: Any): Int = 1
}

inline class Kla1(val default: Int) {
    fun getValue() {
        val prop: Int by Delegate()
    }
}

fun main() {
    Kla1(1).getValue()
}
