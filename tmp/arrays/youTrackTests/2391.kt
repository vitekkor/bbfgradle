// Original bug: KT-39467

fun f() {
    val state1 = Thread.currentThread().state
    when(state1) {
        Thread.State.NEW -> println("new")
        else -> error("noooo!")
    }

    val state2 =
        Thread.currentThread().state
    when(state2) {
        Thread.State.NEW -> println("new")
        else -> error("noooo!")
    }
}
