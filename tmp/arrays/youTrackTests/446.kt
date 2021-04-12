// Original bug: KT-41137

fun main() {
    println("This works: ${TWO.TA.works}")
    TWO.TA.go()
    TWO.TB.go()
}
enum class ONE(val two: TWO) { A(TWO.TA), B(TWO.TB); }
enum class TWO {
    TA { override fun go() { println("should this be null? @TA: $doesntWork") } },
    TB { override fun go() { println("should this be null? @TB: $doesntWork") } };
    abstract fun go()

    val works = setOf(ONE.A, ONE.B)
    val doesntWork = setOf(ONE.A.two, ONE.B.two)
}
