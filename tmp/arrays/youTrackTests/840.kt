// Original bug: KT-44847

data class TestMap(
    private val map: Map<String, Int> = emptyMap()
) : Map<String, Int> by map, Collection<Int> by map.values {

    override val size = map.size

    override fun isEmpty() = map.isEmpty()
}
