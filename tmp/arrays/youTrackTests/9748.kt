// Original bug: KT-5593

import java.io.Closeable

class A : Closeable {
    override fun close() {
        throw UnsupportedOperationException()
    }
}

fun foo(p: Int) {
    A().use {
        when (p) {
            1 -> println(1)
            2 -> println(2)
        }
    }
}
