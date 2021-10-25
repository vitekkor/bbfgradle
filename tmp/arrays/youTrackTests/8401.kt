// Original bug: KT-21620

fun <T> Iterable<T>.all2(predicate: (T) -> Boolean): Boolean {
  if (this is Collection && isEmpty()) return true
  for (element in this) if (!predicate(element)) return false
  return true
}
