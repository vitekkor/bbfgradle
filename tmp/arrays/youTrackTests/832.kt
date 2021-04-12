// Original bug: KT-44793

fun main() {
    println((5.toBigDecimal() / 2.toBigDecimal()).let { "${it::class.simpleName}: $it"})
    println((5.toBigDecimal().div(2.toBigDecimal())).let { "${it::class.simpleName}: $it"})
    println((5.toBigDecimal().divide(2.toBigDecimal())).let { "${it::class.simpleName}: $it"})
}
