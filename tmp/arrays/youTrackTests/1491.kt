// Original bug: KT-22365

fun coroutineBuilder(block: suspend () -> Unit) = Unit

suspend fun suspending(block: () -> Unit) = Unit

fun main(args: Array<String>) {
    `test)`()
}

fun `test)`() {
    val value = 1
    coroutineBuilder {
        suspending {
            println(value)
        }
    }
}
