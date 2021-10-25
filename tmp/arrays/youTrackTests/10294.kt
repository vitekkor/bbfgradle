// Original bug: KT-7402


class A : Appendable {
    override fun append(csq: CharSequence?): Appendable {
        throw UnsupportedOperationException()
    }

    override fun append(csq: CharSequence?, start: Int, end: Int): Appendable {
        throw UnsupportedOperationException()
    }

    override fun append(c: Char): Appendable {
        throw UnsupportedOperationException()
    }
}

fun main(args: Array<String>) {
    A()
}
