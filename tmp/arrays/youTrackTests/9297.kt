// Original bug: KT-12866

fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = null
) {
    try {
        println()
    } catch (thr: Throwable) {
        exceptionHandler?.invoke(thr) ?: Unit
    }
}
