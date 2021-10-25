// Original bug: KT-32655

package foo

fun main() {
    val pairs: List<Pair<String, Int>> = listOf(Pair("a", 1), Pair("a", 2), Pair("b", 1))
    val agg: Map<String, Int> = pairs.groupingBy { it.first }
        .aggregate { _, acc: Int?, element, first ->
            if (first) element.second
            else acc!! + element.second
        }
    println(agg.map { e -> Pair(e.key, e.value) }
        .sortedBy { x -> -x.second })
}
