// Original bug: KT-23311

fun main(args: Array<String>) {
  foo()
}

private fun foo(): Boolean {
  try {
    val x = bar() // Breakpoint: stops
    if (!x) return false
  }
  finally {
    print("Hello?") // Breakpoint is ignored - BUG (line can be reached with "Step Over" though)
  }
  return true
}

private fun bar(): Boolean {
  return true
}
