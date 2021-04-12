// Original bug: KT-37901

class A {
    val index: Int = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as A

        if (index != other.index) return false

        return true
    }

    override fun hashCode(): Int {
        return index
    }
}
