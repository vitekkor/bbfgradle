// Original bug: KT-41117

fun <R> takeLambda(x: (R?) -> R) = x

interface Inv<T>

fun main(x: Inv<String>?, y: Inv<String>) {
  val z = takeLambda { a: Inv<String>? ->
    if (true) {
      x ?: y // OI: Inv<String>?, NI: Inv<String> 
    } else y
  }
}
