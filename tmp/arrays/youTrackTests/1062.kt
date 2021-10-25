// Original bug: KT-45258

fun main() {
    sequence {
        yield("")
        val x = if (true) this else this // implicit cast to Any: CST = SequenceScope<Stub type> & Any & Any?
    }
}
