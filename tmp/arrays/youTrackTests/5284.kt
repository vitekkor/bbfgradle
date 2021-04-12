// Original bug: KT-30693

class A(
    val id: Int,
    val name: String,
    val foo: Number,
    val bar: List<Int>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as A

        if (id != other.id) return false
        if (name != other.name) return false
        if (foo != other.foo) return false
        if (bar != other.bar) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + foo.hashCode()
        result = 31 * result + bar.hashCode()
        return result
    }
}
