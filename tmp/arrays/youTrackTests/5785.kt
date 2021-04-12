// Original bug: KT-32315


class Null1
{
    val v = 1
}

open class Base
{
    init
    {
        val d : Derived = this as Derived // This cast will succeed when Base constructed on Derived init
        print(d.n.v) // Derived hasn't been initialized yet
    }
}

class Derived : Base()
{
    val n = Null1()
}

fun main()
{
    Derived()
}