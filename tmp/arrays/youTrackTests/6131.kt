// Original bug: KT-26676

fun runSuspend(block: suspend () -> Unit): Unit = TODO()
@Deprecated("Use new function", ReplaceWith("runSuspend(block)"))
fun runSuspendOld(block: suspend () -> Unit): Unit = runSuspend(block)

fun usage() {
    runSuspendOld { println() }  // before
    runSuspend({ println() })    // after
}
