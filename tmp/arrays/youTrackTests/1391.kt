// Original bug: KT-44074

fun main() {
    val toSet = setOf<Int>()
    val clusterToReports = toSet.groupBy { it }
    val updatedClusters = mutableListOf<Int>()
    updatedClusters.addAll(clusterToReports.map { it.value[0] }) // step over here
    println(updatedClusters)
}
