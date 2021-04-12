// Original bug: KT-20330

private class C {
    suspend fun run() {
        zzz()
    }

    private suspend fun C.zzz() {  // notice redundant C receiver
        close()
    }

    private suspend fun close() {
    }
}
