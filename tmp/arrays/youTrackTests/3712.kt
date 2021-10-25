// Original bug: KT-31823

interface UpdatableRendering<out T : UpdatableRendering<T>> {
  fun canUpdateFrom(another: @UnsafeVariance T): Boolean
}

internal inline fun <reified T: Any> T.matchesRendering(other: Any): Boolean {
  if (this::class != other::class) return false
  check(other is T)

  return when {
    this !is UpdatableRendering<*> -> true
    else -> this.canUpdateFrom(other as UpdatableRendering<*>)
  }
}
