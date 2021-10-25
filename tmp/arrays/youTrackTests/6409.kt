// Original bug: KT-29590

fun main() {
    registerHandler(handlers = *arrayOf(
        { _ -> }, // OK
        { } // Error: Type inference failed. Expected type mismatch: inferred type is Array<() -> Unit> but Array<out (String) -> Unit> was expected
    ))
}

fun registerHandler(vararg handlers: (String) -> Unit) {
    handlers.forEach { it.invoke("hello") }
}
