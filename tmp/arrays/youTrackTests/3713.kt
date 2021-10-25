// Original bug: KT-31823

interface UpdatableRendering<out T : UpdatableRendering<T>> {
  fun canUpdateFrom(another: @UnsafeVariance T): Boolean
}

internal fun Any.matchesRendering(other: Any): Boolean {
  return when {
    this::class != other::class -> false
    this !is UpdatableRendering<*> -> true
    // If you see a casting error here, it's a lie. https://youtrack.jetbrains.com/issue/KT-31823
    else -> this.canUpdateFrom(other as UpdatableRendering<*>) // Type mismatch: inferred type is UpdatableRendering<*> but CapturedType(*) was expected
  }
}
