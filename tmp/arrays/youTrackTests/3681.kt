// Original bug: KT-32349

abstract class A
{
    abstract val notYetInitialized : Any
    init
    {
         println(notYetInitialized)
    }
}

class B : A()
{
    override val notYetInitialized: Any = "foo"
}

val b = B()

fun main() {}
