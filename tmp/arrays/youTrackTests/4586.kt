// Original bug: KT-35802

import java.util.regex.Pattern

fun main() {
    val input = "a\nb\nc\n"
    val pattern: Pattern = Pattern.compile("$")
    val result = pattern.matcher(input).replaceAll("XXX")

    println("a\nb\ncXXX\nXXX" == result) // true
}
