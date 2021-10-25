// Original bug: KT-20705

abstract class C {

    protected abstract fun ready(): Boolean
    protected abstract suspend fun slow()

    suspend fun optimized() {
        if (!ready()) {
            return slow()
        }
    }

    suspend fun nonOptimized() {
        when {
            ready() -> {}
            else -> slow()
        }
    }
}
