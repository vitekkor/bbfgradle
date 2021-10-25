// Original bug: KT-14611

fun noInlineRun(f: () -> Unit) {
  run { f() }
}

fun doTest() {
  val items = arrayListOf("", "x", "y", "z")
  run {
    noInlineRun {
      items.removeAll { it.isEmpty() }
    }
  }
}
