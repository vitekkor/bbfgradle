// Original bug: KT-25198

import java.util.function.Function

class File(val name: String, val isDirectory: Boolean)

fun foo() {
  val comparator = compareBy<File> { it.isDirectory }.thenComparing(Function { it.name }, String.CASE_INSENSITIVE_ORDER)
}
