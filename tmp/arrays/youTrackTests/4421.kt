// Original bug: KT-34966

import java.time.Instant

data class Action(val datetime: Instant, val isFinal: Boolean)

class Subject(val actions: List<Action>) {
    val lastAction
        get() = actions
            .sortedBy { it.datetime }
            .sortedBy { it.isFinal }
            .lastOrNull()
}

class Subject1(val actions: List<Action>) {
    val lastAction
        get() = actions
            .sortedBy { it.datetime }.maxBy { it.isFinal }
}

fun main() {
    val a = Action(Instant.MAX, true)
    val b = Action(Instant.MIN, true)

    val a1 = Subject(listOf(a, b))
    val b1 = Subject1(listOf(a, b))

    println(a1)
    println(b1)
}
