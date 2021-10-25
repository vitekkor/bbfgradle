// Original bug: KT-31361

class Scope(val value: Int)

suspend fun scoped(block: suspend Scope.() -> Unit) {
    block(Scope(1))
}

suspend fun main() {
    scoped {
        println(value)   // can't evaluate the value
    }
}
