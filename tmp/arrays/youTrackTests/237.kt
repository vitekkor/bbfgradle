// Original bug: KT-44087

@RequiresOptIn("unsafe for usage")
annotation class UnsafeMarker

@UnsafeMarker
class Unsafe

@OptIn(UnsafeMarker::class)
class Safe(private val unsafe: Unsafe)
