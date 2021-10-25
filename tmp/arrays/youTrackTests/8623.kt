// Original bug: KT-12346

private fun nullable(): Int? = null
private fun otherwise(): Int = 1
private fun inc(i: Int) = i + 1

private fun letElvis(): Int =
    nullable()?.let { inc(it) } ?: otherwise()

private fun ifElse(): Int {
  val nullable = nullable()
  return if (nullable != null) inc(nullable)
  else otherwise()
}
