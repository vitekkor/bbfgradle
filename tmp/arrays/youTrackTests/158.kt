// Original bug: KT-28233

//Foo.kt
interface Foo {
    val values: Type
}
//FooImpl.kt
class FooImpl : Foo {
    override val values: Type
        get() = "0"
}
//types.kt
typealias Type = String
