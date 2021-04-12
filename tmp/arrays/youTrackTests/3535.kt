// Original bug: KT-29565

fun main(args: Array<String>){
    val mixedTypes = listOf("This is a String", 1, false)
    val filteredMixedTypes = mixedTypes.filterIsInstance<String>()
    println(filteredMixedTypes)
    // Actual:  [This is a String]
    // Expected: [This is a String]

    val mixedPairs = listOf("String" to "String", "String" to 1, "String" to false, 1 to "String")
    val filteredMixedPairs = mixedPairs.filterIsInstance<Pair<String, String>>()
    println(filteredMixedPairs)
    // Actual: [(String, String), (String, 1), (String, false), (1, String)]
    // Expected: [(String, String)]
}
