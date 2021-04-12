// Original bug: KT-35337

fun <T>List<T>.permutations(): Sequence<List<T>> = asSequence().permutations().map { it.toList() }

private fun <T>Sequence<T>.permutations(): Sequence<Sequence<T>> = sequence {
    for(first in this@permutations) {
        val rest: Sequence<T> = this@permutations.filter { it != first }
        for(r in rest.permutations()){
            yield(sequenceOf(first) + rest)
        }
    }
}

fun main() {
    listOf(1,2,3).permutations().forEach {
        println(it)
    }
}
