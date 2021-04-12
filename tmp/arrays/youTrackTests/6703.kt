// Original bug: KT-23134

typealias Handler = (Int) -> String

fun F()
{
    val handlers: Map<Int, Handler> = mapOf(
        42 to { _ -> "Some string"}
//              ^^^^ Redundant lambda arrow
    )
}
