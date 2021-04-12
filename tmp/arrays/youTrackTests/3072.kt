// Original bug: KT-39749

interface Tree {
    val root: Int?
    fun apply(event: Int)
}

private class TreeImpl(override val root: Int?) : Tree {
    override fun apply(event: Int) {
        TODO("Not yet implemented")
    }
}
