// Original bug: KT-31535

fun bug(worker: Worker<Unit>) {
    stateless<Boolean, Nothing, Unit> {
        onWorkerOutput(worker) // Warning: [IMPLICIT_NOTHING_AS_TYPE_PARAMETER] One of the type variables was implicitly inferred to Nothing. Please, specify type arguments explicitly to hide this warning.

        onWorkerOutput<Nothing, Nothing, Unit>(worker) // Inspection: Remove explicit type arguments
    }
}

fun <StateT, OutputT : Any, T> RenderContext<StateT, OutputT>.onWorkerOutput(worker: Worker<T>): Unit = Unit

fun <InputT, OutputT : Any, RenderingT> stateless(
    render: RenderContext<Nothing, OutputT>.(input: InputT) -> RenderingT
) { }

interface Worker<out T>

interface RenderContext<StateT, in OutputT : Any>
