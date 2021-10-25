// Original bug: KT-10651

public class MyClass
public class MyClass2

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()
    MyClass::class
    val end = System.currentTimeMillis()
    println("KClass: ${end-start} ms")


    val start2 = System.currentTimeMillis()
    MyClass2::class.java
    val end2 = System.currentTimeMillis()
    println("JavaClass: ${end2-start2} ms")
}
