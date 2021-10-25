// Original bug: KT-25295

interface IFoo {
    fun foo() = "O"
    fun bar(): String
}

inline class Str(val string: String) : IFoo {
    override fun bar() = string
}

fun box(): String {
    val k = Str("K")
    return k.foo() + k.bar()
}
