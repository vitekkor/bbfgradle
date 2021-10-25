// Original bug: KT-35477

inline fun <T> tryCatch(block: () -> T, onSuccess: (T) -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        return
    }.also { onSuccess(it) }
}
