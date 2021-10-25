// Original bug: KT-30123

fun main() {
    val s = ""
    if (!s.isEmpty()) { // 1
    }

    if (!s.isNotEmpty()) { // 2
    }

    val a = listOf(1)
    if (!a.isEmpty()) { // 3
    }

    if (!a.isNotEmpty()) { // 4
    }
}
