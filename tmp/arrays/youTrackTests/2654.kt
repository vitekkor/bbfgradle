// Original bug: KT-35526

enum class Type {
    HYDRO,
    PYRO
}

fun select(t: Type): Int {
    // Intention: Eliminate argument of 'when'
    return when (t) {
        Type.HYDRO -> 1
        Type.PYRO -> 42
        // no 'else' branch
    }
}
