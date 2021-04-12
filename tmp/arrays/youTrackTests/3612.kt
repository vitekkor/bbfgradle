// Original bug: KT-26878

fun main(args: Array<String>) {
    val mySequence = sequenceOf("agct", "aagct", "aaa", "gcta", "ggccat")

    val num = mySequence
            .filter {it.length > 3}
            .map { it.length }
            .sum()
}
