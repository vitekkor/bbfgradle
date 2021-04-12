// Original bug: KT-28751

data class IA(val ia: IntArray)

fun main(args: Array<String>) {
    val ia0 = IA(intArrayOf(0))
    val ia1 = IA(intArrayOf(0))
    println("ia0: ${ia0}, ia1: ${ia1}, ia0.ia: ${ia0.ia}, ia1.ia: ${ia1.ia}")
    println("ia0.hash: ${ia0.hashCode()}, ia1.hash: ${ia1.hashCode()}, ia0.ia.hash: ${ia0.ia.hashCode()}, ia1.ia.hash: ${ia1.ia.hashCode()}")
    println("ia0 == ia1: ${ia0 == ia1}, ia0.hash == ia1.hash: ${ia0.hashCode() == ia1.hashCode()}")
    println("ia0.ia == ia1.ia: ${ia0.ia == ia1.ia}, ia0.ia.hash == ia1.ia.hash: ${ia0.ia.hashCode() == ia1.ia.hashCode()}")
}
