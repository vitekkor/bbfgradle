// Original bug: KT-23121

data class TxDef<out S>(val from: S?, val to: List<S>)

enum class ExampleState {
    START,
    PROGRESS,
    END
}

val example1 = TxDef(null, listOf(ExampleState.START))
val example2 = TxDef(ExampleState.PROGRESS, listOf(ExampleState.START, ExampleState.END))
val exampleBug = TxDef(listOf(ExampleState.PROGRESS), listOf(ExampleState.START, ExampleState.END))
