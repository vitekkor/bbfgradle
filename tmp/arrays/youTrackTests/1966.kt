// Original bug: KT-43045

interface IFoo<T : IFoo<T>> {
    fun T.foo(): String = bar()
    fun bar(): String
}


inline class L(val x: Long) : IFoo<L> {
    override fun bar(): String = "OK"
}
