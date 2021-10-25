// Original bug: KT-35528

enum class Type {
    HYDRO,
    PYRO
}

fun select(t: Type): Int {
    // Intention on 'when': Replace 'when' with 'if'
    // Intention on variable 'i': Split property declaration
    return when (val i = t.ordinal) {
        0 -> 1
        1 -> 42
        else -> i
    }
}
