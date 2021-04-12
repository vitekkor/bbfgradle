// Original bug: KT-25763

fun main(args: Array<String>) {
    Foo().bar()
}

interface IFoo {
    fun bar(x: Int = 42)
}

class Foo : IFoo {
    override fun bar(x: Int) {
        println(x)
    }
}
