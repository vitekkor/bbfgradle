// Original bug: KT-14304

interface AA {
    fun foo()
}
class Demo {
    val some = object : AA {
        override fun foo() {
            if (state == State.A)
                state = State.B

            if (state != State.B) // "Variable `state` must be initialized" here
                println()
        }
    }

    private enum class State { A, B, C }
    private var state: State = State.A
}
