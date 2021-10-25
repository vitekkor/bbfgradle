// Original bug: KT-12729

internal inline operator fun <reified T> Array<T>.minus(elements: Array<out T>): Array<T> {
  var j = 0;
  return Array(size - elements.size) {
    while (this[j] in elements) {
      j++
    }
    this[j++]
  }
}
