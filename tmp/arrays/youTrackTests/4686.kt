// Original bug: KT-31102

class Test {
    fun bar() = 1

    fun test(x: Int) {
        val f1: () -> Int = select({this.bar()}, this::bar) // TYPE_MISMATCH on lambda
        val f2 = select({this.bar()}, this::bar) // Same
    }
}

fun <K> select(x: K, y: K): K = TODO()
