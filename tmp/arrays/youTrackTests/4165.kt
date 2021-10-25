// Original bug: KT-13800

class PromiseState<T>(var result: T?)

interface ReactComponentSpec<T>

fun <TState> ReactComponentSpec<TState>.setState(newState: TState) {}

class PromiseComponent<T> : ReactComponentSpec<PromiseState<T>> {
    fun componentDidMount() {
        setState(PromiseState(null)) // TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS
        
        setState(PromiseState(null as T?)) // ok
    }
}
