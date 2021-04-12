// Original bug: KT-32315

class Null1
{
    val v = 1
}

class A
{
    init
    {
        printN1() // direct println(n1.v) will not compile here. deferring it with function to avoid check with static analyzer
    }

    val n1 = Null1() //initialized after init, because lower in code (it seems in fact kotlin works this way unlike C++)
    fun printN1() = println(n1.v)
}


fun main()
{
    val a = A()
    println("If you read this, there is no null pointer exception; $a")
}