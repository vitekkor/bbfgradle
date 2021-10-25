// Original bug: KT-25283

abstract class Number {
    abstract fun count(): Int
}

class One : Number() {
    override fun count(): Int = 1
}

fun test(n: Number) {
    n.count()
}
