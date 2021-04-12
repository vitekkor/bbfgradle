// Original bug: KT-45964

enum class E {
    E1, E2
}

fun main() {
    val e = E.E1

    when {  // 1
        e === E.E1 -> println("b")
        e === E.E2 -> println("c")
    }

    when (e) {  // 2
        E.E1 -> println("b")
        E.E2 -> println("c")
    }

    when (e.ordinal) {  // 3
        E.E1.ordinal -> println("b")
        E.E2.ordinal -> println("c")
    }
}
