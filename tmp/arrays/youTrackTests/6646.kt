// Original bug: KT-13846

open class SuperClass(arg: () -> Any)

object obj {

    fun foo(arg: Int) {}

    class foo : SuperClass(::foo)
}

fun main(args: Array<String>) {
    obj.foo()
}
