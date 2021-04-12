// Original bug: KT-30772

interface WithComparableLongProperty {

    val prop: Long

    companion object {
        val COMPARATOR = Comparator<WithComparableLongProperty> { o1, o2 ->
            when {
                o1.prop < o2.prop -> -1
                o1.prop > o2.prop -> 1
                else -> 0
            }
        }
    }
}

data class Data(val value: Long) : WithComparableLongProperty {
    override val prop get() = value
}
