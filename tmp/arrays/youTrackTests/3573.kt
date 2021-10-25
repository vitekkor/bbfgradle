// Original bug: KT-38596

abstract class Parent {
    abstract fun get(): Any
}
data class Child(val int: Int): Parent() {
    override fun get(): Any {
        TODO("Not yet implemented")
    }
}
