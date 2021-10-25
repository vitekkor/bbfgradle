// Original bug: KT-20708

abstract class C {

    protected abstract fun ready(): Boolean
    protected abstract suspend fun slow()

    suspend fun notOptimized1() {
        if (ready()) {
            return
        } else {
            slow()
        }
    }

    suspend fun notOptimized2() {
        if (ready()) {
            return
        }

        slow()
    }

    suspend fun optimized() {
        if (ready()) {
            return
        }

        return slow() // notice return
    }
}
