// Original bug: KT-25197

class File(val name: String, val isDirectory: Boolean)

fun foo() {
  val comparator = compareBy<File> { it.isDirectory }.thenComparing({ file: File -> file.name}, String.CASE_INSENSITIVE_ORDER)
}
