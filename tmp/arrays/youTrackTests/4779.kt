// Original bug: KT-35527

enum class Type {
    HYDRO,
    PYRO
}

fun select(t: Type): Int {
    // Intention: Replace 'when' with 'if'
    return when (t) {
        Type.HYDRO -> 1
        Type.PYRO -> 42
        // no 'else' branch
    }
}
