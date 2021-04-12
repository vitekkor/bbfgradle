// Original bug: KT-19784

suspend fun doWork() {}

suspend fun callDoWork() {
    doWork() // no tail call optimisation
}

suspend fun callDoWorkReturn() {
    return doWork() // optimised, no continuation or state machine
}
