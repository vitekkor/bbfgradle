// Original bug: KT-38134

data class FunctionHolder<out T : Any>(val f: (@UnsafeVariance T) -> Unit) {
  fun f2(v: @UnsafeVariance T) {}
}

// Warning about "out" being redundant. And if it's removed, this code compiles.
fun caller(holder: FunctionHolder<out Any>) {
  // IDE shows both of these functions as taking Any in code completion, but compiler
  // expects Nothing, and IDE parameter docs also show Nothing.
  holder.f(Any())
  holder.f2(Any())
}
