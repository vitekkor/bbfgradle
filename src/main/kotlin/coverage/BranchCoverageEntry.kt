package coverage

data class BranchCoverageEntry(
    val UID: Int,
    val fullyQualifiedName: String,
    val lineNumber: Int,
    val branch: Boolean
) {
    fun isSameEntries(other: BranchCoverageEntry): Boolean {
        return true
    }
}