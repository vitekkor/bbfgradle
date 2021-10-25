// Original bug: KT-34041

class X {

    suspend fun suspending() {}

    suspend fun test() {
        logError(::suspending)
    }

    suspend inline fun runBlock(block: suspend () -> Unit) {
        logError(block)
    }

    suspend inline fun logError(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            //Log Exception
        }
    }
}

