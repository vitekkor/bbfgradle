// Original bug: KT-11734

const val State1 = 1
const val State2 = 2

fun switchStateConst(state: Int) = when(state) {
    State1 -> {}
    State2 -> {}
    else -> error("unknown")
}

fun switchStateLiteral(state: Int) = when(state) {
    1 -> {}
    2 -> {}
    else -> error("unknown")
}
