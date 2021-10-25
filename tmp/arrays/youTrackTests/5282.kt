// Original bug: KT-25934

class C(val value: Double) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as C

        if (value != other.value) return false // NB: IEEE 754 equality

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
