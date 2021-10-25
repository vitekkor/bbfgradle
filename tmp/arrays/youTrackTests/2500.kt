// Original bug: KT-4107

// https://pl.kotl.in/rJVTtIjb4

object A : FixToString() {
    object B : FixToString() {        
    }
}
fun main() {
    println(A)  // "A" 
    println(A.B) // "B"
}
abstract class FixToString {
      private val simpleName : String by lazy { this::class.simpleName ?: super.toString() }
      override fun toString() = simpleName
}
