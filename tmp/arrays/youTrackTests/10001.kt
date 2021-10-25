// Original bug: KT-10253

interface A {
    var a: Int
}
class AJunior : A {
    override var a: Int = 3
}

fun main(args: Array<String>) {
    val aJunior = AJunior()
    println(aJunior.a)
    aJunior.a = 4
    println(aJunior.a)
}
