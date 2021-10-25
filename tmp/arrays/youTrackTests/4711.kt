// Original bug: KT-32106

class Query<out T : Any> private constructor(
    private val result: T?,
    private val error: Throwable?,
    val inProgress: Boolean
) {
    companion object {
        val inProgress = Query(null, null, true)
        fun forError(e: Throwable) = Query(null, e, false)
        fun <T : Any> forResult(result: T) = Query(result, null, false)
    }
}
