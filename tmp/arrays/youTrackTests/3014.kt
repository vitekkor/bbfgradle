// Original bug: KT-28483

package test

interface IFoo

inline class Z(val x: Any?) : IFoo

interface IBar1 {
    fun bar1(): Any
}

interface IBar2 {
    fun bar2(): IFoo
}

object Impl : IBar1, IBar2 {
    override fun bar1() = Z("")
    override fun bar2() = Z("")
}

fun main() {
    val test1: Any = (Impl as IBar1).bar1()
    val test2: Any = (Impl as IBar2).bar2()

    if (test1 !is Z) throw AssertionError() // fails as well
    if (test2 !is Z) throw AssertionError() // fine
}
