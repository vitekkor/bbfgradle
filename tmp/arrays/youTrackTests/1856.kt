// Original bug: KT-12456

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(var b: Base) : Base by b  //no compile-error, no warning

fun main(args: Array<String>){
    val b = BaseImpl(10)
    val derived = Derived(b)

    //....
    //somewhere within the code:
    derived.b = BaseImpl(20) //no compile-error, no warning
    derived.print() //assert 20, but print 10
}
