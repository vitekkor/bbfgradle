// Original bug: KT-43546

fun foo() {
  var v = 1..10
  if (extracted(v)) return
  print(10)
}

private fun extracted(v: IntRange): Boolean {
  v.forEach { it ->
    if (it == 3) {
      return true
    }
  }
  return false
}

