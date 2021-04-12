// Original bug: KT-19650

interface Iface<in T>  {
    fun f(t: T = error("no T passed")): Any
}

class Impl : Iface<Int> {
    override fun f(t: Int): Any = this
}

fun main(args: Array<String>) {
    val i = Impl()
    i.f()
}
