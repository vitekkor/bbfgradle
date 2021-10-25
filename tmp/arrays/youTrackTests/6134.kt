// Original bug: KT-31543

package whatever

import org.intellij.lang.annotations.Language

fun asRegexes(@Language("RegExp") vararg regexes: String) = regexes.map { it.toRegex() }.toList()

val regexes = asRegexes(
  ".*[a-z]+",
  "^(.a?).*"
)
