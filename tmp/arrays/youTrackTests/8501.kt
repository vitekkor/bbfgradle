// Original bug: KT-12868

class Foo(val start: Int, val length: Int) {
    private var idx = if (start < 0) throw Exception("idx must be >= 0") else start
}
fun main(args: Array<String>) {
    val f = Foo(10, 10)
}
