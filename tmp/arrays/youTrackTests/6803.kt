// Original bug: KT-9437

interface A<T : Any>  {
    fun foo(x : T? = null)
}

interface B {
    fun foo(x : String? = null)
}

interface C<T : Any> : A<T>, B

object D : C<String> {
    override fun foo(x: String?) { }
}

fun main(args: Array<String>) {
    D.foo()
}
