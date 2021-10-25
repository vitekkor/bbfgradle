// Original bug: KT-16978

private suspend fun foo(): Int {
    null?.let {
        return bar() // Couldn't inline method call 'let'
    }
    return 23
}

private suspend fun bar(): Int {
    return 42
}
