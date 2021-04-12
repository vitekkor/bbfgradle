// Original bug: KT-40858

@RequiresOptIn(message = "This API is required to be called from existing transaction context.")
@Retention(AnnotationRetention.SOURCE)
annotation class RequiresTransactionContext
