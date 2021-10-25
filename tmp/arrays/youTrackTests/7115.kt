// Original bug: KT-24156

operator fun String.iterator(): IntIterator = object : IntIterator() {
  private var index = 0

  override fun nextInt() = codePointAt(index).also {
    index += Character.charCount(it)
  }

  override fun hasNext(): Boolean = index < length
}
