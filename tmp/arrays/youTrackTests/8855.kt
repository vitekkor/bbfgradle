// Original bug: KT-7984

fun foo(x : String, y : String = x) // OK 
{
}

fun main(args: Array<String>) 
{
    fun bar(x : String, y : String = x) // Unresolved reference: x
    {
    }
}
