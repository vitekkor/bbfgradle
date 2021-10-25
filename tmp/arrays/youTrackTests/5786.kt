// Original bug: KT-32315

class Null1
{
    val v = 1
}

abstract class A
{
    abstract val n1 : Null1

    init
    {
        println(n1.v) //leaking this in constructor. 'A' init called before A1 init as it should be in inheritance chain.
        //There is no compilation message here, but idea warns about leaking this. I think this is possible to improve static analyzer to discover this case.
        //If static analyzer will finally be able to found accessing abstract value before initialization it can be fooled with deferring by function as in DeferredUninitValCall.kt example
    }
}

class A1 : A()
{
    override val n1: Null1 = Null1()
}

fun main()
{
    val a = A1()
    println("If you read this, there is no null pointer exception; $a")
}