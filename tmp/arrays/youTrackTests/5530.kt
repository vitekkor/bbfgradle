// Original bug: KT-25198

class File(val name: String, val isDirectory: Boolean)

fun foo() {
  val comparator = compareBy<File> { it.isDirectory }.thenComparing({ it.name }, String.CASE_INSENSITIVE_ORDER)
}
