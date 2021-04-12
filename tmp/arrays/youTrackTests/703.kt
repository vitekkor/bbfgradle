// Original bug: KT-42815

inline fun myRun(x: () -> String) = x()

class C {
    val x: String
    init {
        val y = myRun { { "OK" }() }
        x = y
    }

    constructor(y: Int)
    constructor(y: String)
}

fun box(): String = C("").x

fun main() = println(box())
