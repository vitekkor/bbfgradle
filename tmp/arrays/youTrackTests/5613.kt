// Original bug: KT-32751

class Composed() {
    suspend operator fun component1() = 1
    suspend fun callSuspend() {
        val (one) = Composed() // no gutter icon
        val first = Composed().component1() // gutter icon
    }
}
