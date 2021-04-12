// Original bug: KT-5497

import java.io.Closeable

class Resource : Closeable {
    override fun close() {
        println("closed")
    }
}

fun main(args: Array<String>) {
    Resource().use {
        if (args.size == 0) return
        println(args.size)
    }
}
