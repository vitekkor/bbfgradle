// Original bug: KT-30902

fun foo(x: List<String>): Int {

    return when (val y = x.reversed().sorted().singleOrNull { it.startsWith("0") }
        ?: throw AssertionError("Nothing found: $x")) {
        "01" -> y.substring(1).toInt()
        else -> 2
    }
}
