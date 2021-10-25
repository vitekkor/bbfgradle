// Original bug: KT-16451

interface Increment<T> {
    fun T.increment(): T
}

class IntIncrement : Increment<Int> {
    inline final override fun Int.increment(): Int = this + 1
}

inline fun IntIncrement.run() {
    val x = 3.increment()
}

inline fun <reified I: Increment<Int>> I.runReified() {
    val x = 3.increment()
}

fun test() {
    val i = IntIncrement()
    i.run()
    i.runReified()
}
