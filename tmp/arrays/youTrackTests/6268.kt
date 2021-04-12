// Original bug: KT-29247

import java.util.regex.Pattern

private const val GROUP_NAME = "name"
private val PATTERN = Pattern.compile("(?<$GROUP_NAME>[a-z])")

fun main() {
  val matcher = PATTERN.matcher("a")
  matcher.find()
  println(matcher.group(GROUP_NAME))
}
