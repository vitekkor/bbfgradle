// Original bug: KT-18683

class A(val name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is A) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
