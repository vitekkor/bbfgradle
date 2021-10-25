// Original bug: KT-26458

fun foooo(): String {
    return bar(fun(): String {
        return throw IllegalArgumentException()
    })
}

inline fun <reified T> bar(action: () -> T): T {
    return action()
}
