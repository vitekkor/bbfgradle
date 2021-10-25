// Original bug: KT-3630

data class StatePair<S, A>(val state: S, val value: A)

class State<S, A>(val runState: (S) -> StatePair<S, A>)

fun <S, A> State<S, A>.evalState(s: S): A = this.runState(s).value

val <S, A> State<S, A>.bugEvalState: (S) -> A
    get() = { this.runState(it).value }

fun <S, A> foo(st: State<S, A>, initState: S) {
    st.evalState(initState)          // ok
    st.bugEvalState(initState)       // failed: found S, expected S
}
