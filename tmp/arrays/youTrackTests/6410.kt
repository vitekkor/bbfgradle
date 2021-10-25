// Original bug: KT-29590

fun main() {
    registerHandler(
        { _ ->
            Unit
        },
        { _ ->
            Unit
            Unit
        }
    )
}

fun registerHandler(vararg handlers: (String) -> Unit) {
    handlers.forEach { it("hello") }
}
