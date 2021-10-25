// Original bug: KT-9221

fun main(args: Array<String>) {
	println(Base.Child2(10))
}

sealed class Base {
    class Child1(val str: String): Base()
    class Child2(val int: Int): Base()
    override fun toString(): String {
        return when (this) {
            is Child1 -> "${str}"
            is Child2 -> "${int}"
        }
    }
}
