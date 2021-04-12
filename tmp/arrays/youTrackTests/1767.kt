// Original bug: KT-43546

fun foo() {
  var v = 1..10
  extracted(v) //broken control-flow!!!
  print(10)
}

private fun extracted(v: IntRange) {
  v.forEach {
    if (it == 3) {
      return
    }
  }
}

