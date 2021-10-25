// Original bug: KT-32453

fun main() {
    // BUG IS HERE. Expected: [1], Actual []
    println(
        sequenceOf(1, 2, 3)
            .filterNot { (it == 2) or (it == 3) }
            .toList()
    )
    
    // NOTE 1: Replacing the 'or' operator by || fixes the problem
    println(
        sequenceOf(1, 2, 3)
            .filterNot { (it == 2) || (it == 3) }
            .toList()
    )

    // NOTE 2: Using lists rather than sequences also fixes the problem
    println(
        listOf(1, 2, 3)
            .filterNot { (it == 2) || (it == 3) }
    )
}
