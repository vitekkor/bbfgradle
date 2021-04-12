// Original bug: KT-38793

fun <T> consume(t: T) {}
fun consumeSpecialized(t: suspend Unit.(Unit) -> Unit) {}
fun main() {
    consume<suspend Unit.(Unit) -> Unit> { /* ok */ }
    consume<suspend Unit.(Unit) -> Unit> { u -> /* ok */ }
    consume<suspend Unit.(Unit) -> Unit> { u: Unit -> /*
        Type mismatch.
        Required: suspend Unit.(Unit) â Unit
        Found: (Unit) â Unit
  */}
    consumeSpecialized { u: Unit -> /* ok */ }
    
    val func: suspend Unit.(Unit) -> Unit = { u: Unit -> /* ok*/ }
    consume<suspend Unit.(Unit) -> Unit>(func) // ok!
}
