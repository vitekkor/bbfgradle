// Original bug: KT-32051

interface LevelA
interface LevelB : LevelA

class BiType<out X, out Y> {
    fun <X> pullXb(x: X): BiType<X, LevelB> = TODO()
    fun <Y> pullYb(y: Y): BiType<LevelB, Y> = TODO()
    fun <X> pullXn(x: X): BiType<X, Nothing> = TODO()
    fun <Y> pullYn(y: Y): BiType<Nothing, Y> = TODO()
}

fun <X> adjustIt(fn: () -> X): X = TODO()
fun <X> adjustIt(f1: () -> X, f2: () -> X): X = TODO()

fun <X> callAdjustIt(t: BiType<*, *>, x: X, level: LevelA) {
    val x1 = adjustIt({ t.pullXb(x) }) // BiType<X, LevelB> with both inference algorithms.
    val x2 = adjustIt({ t.pullXn(x) }) // BiType<X, Nothing>, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER.

    val x3 = adjustIt({ t.pullXb(x) }, { t.pullYb(level) }) // BiType<Any?, LevelA> with both inference algorithms.
    val x4 = adjustIt({ t.pullXn(x) }, { t.pullYn(level) }) // BiType<X, LevelA>, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER.
}
