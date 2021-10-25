// Original bug: KT-33440

inline fun runInTry(block: () -> Unit) {
    try {
        block()
    } finally {

    }
}

// Removing "suspend" from block solves the issue
suspend inline fun crashTheCompiler(
    block: suspend () -> Unit
) = runInTry { block() }
