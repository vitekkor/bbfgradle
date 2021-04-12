// Original bug: KT-31727

abstract class MyClass(val foo: String)

fun main()  {
    val a = "hello"
    val b = "world"

    val obj = object: MyClass(a+b) {}
    println(obj.foo) // breakpoint
}
