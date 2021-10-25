// Original bug: KT-36192

fun main() {
    if (currentTagOrNull != null) {
        println(currentTagOrNull)
    }
}

private var tagStack = listOf(1, 2)

val currentTagOrNull
    get() = tagStack.lastOrNull()
