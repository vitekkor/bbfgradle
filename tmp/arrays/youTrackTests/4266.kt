// Original bug: KT-32430

fun readInt(cl: () -> Int?) = cl()

val v: Int? = readInt {
    var x: Int? = null
    x = 1
    x.inc() // IDE only: [SMARTCAST_IMPOSSIBLE] Smart cast to 'Int' is impossible, because 'x' is a local variable that is captured by a changing closure
}
