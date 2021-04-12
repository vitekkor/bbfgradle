// Original bug: KT-45408

fun logLifecycleEvent() {
    ICLog.tag().d()
}

object ICLog {
    @JvmStatic
    fun d() {

    }

    @JvmStatic
    fun tag(): ICLog {
        return this
    }
}
