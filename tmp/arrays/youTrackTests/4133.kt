// Original bug: KT-7758

class A(
    val arg: Pair<(String) -> Boolean, String>
)
val a = A(
    Pair({ it.contains("q") }, "") // Error: Type inference failed. Expected type mismatch: found: kotlin.Pair<() -> [ERROR : <ERROR FUNCTION RETURN TYPE>], kotlin.String> required: kotlin.Pair<(kotlin.String) -> kotlin.Boolean, kotlin.String>
)
