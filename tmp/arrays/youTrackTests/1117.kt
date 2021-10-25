// Original bug: KT-27189

suspend fun f(i: Int): Long {
    return if (i > 0) 1L else f_2()
    // if (i > 0) return 1L   // this version doesn't lead to state machine generation
    // return f_2()
}

private suspend fun f_2(): Long = TODO()
