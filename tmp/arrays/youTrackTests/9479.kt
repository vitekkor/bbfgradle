// Original bug: KT-13245

typealias Action = Any?

interface Reducer<State> : (Action) -> State, (Action, State) -> State

inline fun <State> reducer(
        crossinline reduceInitial: (Action) -> State,
        crossinline reduce: (Action, State) -> State) =
        object : Reducer<State> {
            override fun invoke(action: Action): State = reduceInitial(action)
            override fun invoke(action: Action, state: State): State = reduce(action, state)
        }

fun test() {

    // OK
    val r = reducer({ action -> 0 }, { action, state -> state })
}
