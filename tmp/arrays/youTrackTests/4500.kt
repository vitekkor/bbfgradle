// Original bug: KT-36197

fun square(value: Int): Int = TODO()

fun List<Int>.forEachSuspending(block: suspend (value: Int) -> Unit): Unit = TODO()

fun main() {
    listOf(1, 2).forEachSuspending(::square) // Type mismatch.   Required: suspend (Int) â Unit   Found: KFunction1<Int, Unit>
}
