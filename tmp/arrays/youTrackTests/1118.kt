// Original bug: KT-23824

abstract class C {
    protected abstract fun ready(): Boolean
    protected abstract suspend fun slow(): Boolean

    suspend fun optimized(): Boolean {
        if(ready()){
            return true
        }
        else {
            return slow()
        }
    }

    suspend fun notOptimized(): Boolean {
        return if(ready()){
            true
        }
        else {
            slow()
        }
    }
}
