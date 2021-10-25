// Original bug: KT-27501

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Foo internal constructor(val a: Any?) {
    init {
        println(this)
    }
}

fun main(args: Array<String>) {
    val foo = Foo(1)
    val l = listOf(foo)
}
