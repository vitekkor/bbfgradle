// Original bug: KT-43400

open class C {
    open val size: Int = 1
    fun iterator(): Iterator<Int> = TODO()

    public open fun isEmpty(): kotlin.Boolean = TODO()

    public open fun indexOf(element: kotlin.Int): kotlin.Int = TODO()

}

abstract class A : C()

abstract class B : A(), List<Int> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: Int): Boolean {
        TODO("Not yet implemented")
    }
  
  //indexOf(I)I [public, bridge]
}
