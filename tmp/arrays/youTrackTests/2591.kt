// Original bug: KT-26458

fun foo(): String {
    val action: () -> String = {          // try to inline `action`
        throw IllegalArgumentException()
    }
    return bar(action)
}

inline fun <reified T> bar(action: () -> T): T {
    return action()
}
