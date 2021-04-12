// Original bug: KT-257

class A<T>(val t: T) {}
class B<R>(val r: R) {}

fun main(args: Array<String>) {
    val ai = A<Int>(1)
    val aai = A<A<Int>>(ai)
    System.out?.println(ai.t)         //prints 1
    System.out?.println(aai.t === ai) //prints true
    System.out?.println(aai.t.t)      //throws exception

    val abi = A<B<Int>>(B<Int>(1))
    System.out?.println(abi.t.r)      //throws the same exception; NoSuchMethodError: my.B.getR()I
}
