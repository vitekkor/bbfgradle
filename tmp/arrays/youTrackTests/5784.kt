// Original bug: KT-32315

package main

class Null1
{
    val v = 1
}

fun accessNull() : Int { println(a.v); return 0 } //Static analyzer does'n know a is not initialized

private val b = accessNull() //Static analyzer doesn't know what inside accessNull()
private val a = Null1()

fun main()
{
    println("If you read this, there is no null pointer exception")
}