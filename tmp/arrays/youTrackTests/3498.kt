// Original bug: KT-37665

fun <T> runBlock(block: (Inv<T>) -> Unit): T = null as T

class Inv<T>(x: T)
class Bar

fun takeInv(x: Inv<Bar>) {}

fun main(): Bar {
    return try {
        runBlock { // implicitly inferred T to Nothing
            takeInv(it) // OK in OI, it as Inv<Bar>; NI: Type mismatch. Required: Inv<Bar>; Found: Inv<Nothing>
        }
    } catch (e: Exception) {
        throw IllegalArgumentException("message", e)
    }
}
