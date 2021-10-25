// Original bug: KT-18790

internal interface Resetable {
    fun set(value: Int)
}

interface Counter {
    fun inc() : Int;
}


fun main(vararg args: String) {
    val counter: Counter = reset(0, open());
    //                     ^--- the code should be works fine, but it can't
}

private fun <T> reset(initial: Int, counter: T): Counter where T : Counter, T : Resetable {
    TODO()
}

private inline fun <T> open(): T where T : Counter, T : Resetable {
    TODO()
}
