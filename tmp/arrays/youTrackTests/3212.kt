// Original bug: KT-39204

fun main() {
    println(25.myRec())
}

fun Int.myRec(): Int {
    if (this <= 0) return 0

    val inner = { minus(1).myRec() }

    return inner() + minus(1).myRec()
}
