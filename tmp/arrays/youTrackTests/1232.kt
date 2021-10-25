// Original bug: KT-16222

suspend fun execute() {
    while (true) {
        val buffer = receive()
        consume(buffer)
        println("GC should collect buffer here")
    }
}

suspend fun receive() = Buffer()

suspend fun consume(buffer: Buffer) = Unit

class Buffer
