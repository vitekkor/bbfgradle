// Original bug: KT-42255

fun foo(list: List<String>): String {
  val firstOrNull = list.firstOrNull()
  val first = if (firstOrNull != null) firstOrNull else error("empty")
  return "First length: ${first.length}"
}
