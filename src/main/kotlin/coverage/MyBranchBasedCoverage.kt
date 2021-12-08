package coverage

object MyBranchBasedCoverage {
    val methodProbes = mutableMapOf<BranchCoverageEntry, Int>()

    @JvmStatic
    fun putEntry(entry: BranchCoverageEntry) {
        val currentAmount = methodProbes[entry]
        if (currentAmount == null) {
            methodProbes[entry] = 1
        } else {
            methodProbes[entry] = currentAmount + 1
        }
    }
}