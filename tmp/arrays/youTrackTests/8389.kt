// Original bug: KT-11856

interface Out<out T> {
    val t: T
}

class Inv<T>(override var t: T) : Out<T> {
}

fun main(args: Array<String>) {
    val int: Out<Int> = Inv(1)
    val any: Out<Any> = int
    val mutAny: Inv<Any> = any as Inv<Any>
    mutAny.t = "fail"
    println(int.t)
}
