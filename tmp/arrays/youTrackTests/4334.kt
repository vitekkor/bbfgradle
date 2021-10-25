// Original bug: KT-31601

class C

fun C?.foo(): String {
    return if (this != null) this.toString() else "it's null"
}

fun String.getC(): C? {
    return null
}

fun bar(s: String?): String? {
    return s?.let { it.getC().foo() }
}

fun main() {
    println(bar("a"))
}
