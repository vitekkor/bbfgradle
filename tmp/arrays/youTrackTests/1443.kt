// Original bug: KT-33440

// Either removing "suspend" from block OR using block() solves the issue
suspend inline fun crashTheCompiler(
    block: suspend () -> Unit
) = try {
    block.invoke()
} finally {

}
