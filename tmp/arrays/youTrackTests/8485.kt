// Original bug: KT-20774

class Foo {
    lateinit var bar: String

    fun callable(): Boolean = true

    fun test(): Boolean {
        if (!::bar.isInitialized) {
            bar = "a"
            return false
        }
        return (::callable)()
    }
}

fun main(args: Array<String>) {
    val foo = Foo()
    println(foo.test())
    println(foo.bar)
}
