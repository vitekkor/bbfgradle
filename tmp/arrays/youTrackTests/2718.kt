// Original bug: KT-38801

open class A {
    open val servers: List<C>
        get() = findAndExpand({"hi"})
            .mapNotNull { B.foo<C>(it) } // remove explicit type arguments

    fun findAndExpand(vararg path: () -> String): List<String> = TODO()
}

object B {
    inline fun <reified T : C> foo(bar: String?): T? = TODO()
}

open class C
class D : C()
