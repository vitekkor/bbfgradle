// Original bug: KT-9495

abstract class A<T> {
    abstract fun broken(vararg values: T): String
}

class B : A<Int>() {
    override fun broken(values: Array<out Int>): String {
        return broken(*values.toIntArray())
    }

    fun broken(vararg values: Int): String {
        return values.joinToString { it.toString() }
    }
}
