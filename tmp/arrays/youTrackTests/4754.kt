// Original bug: KT-20330

private class C {
    suspend fun run() {
        close() // select close invocation and extract it as a function in class C
    }

    private suspend fun close() {
    }
}
