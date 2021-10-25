// Original bug: KT-259

class A() {}
class B<T>() {}

fun main(args: Array<String>) {
    val a = A()
    System.out?.println(a is A)   //true
    System.out?.println(a is A?)  //true

    val b = B<String>()
    System.out?.println(b is B<String>)   //true
    System.out?.println(b is B<String>?)  //false !!!

    val v = b as B<String>   //ok
    val u = b as B<String>?  //TypeCastException
}
