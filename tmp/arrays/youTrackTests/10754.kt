// Original bug: KT-1976

class A {
    public val f : ()->Unit = {}
}

fun main(args : Array<String>) {
    val a = A()
    val f = a.f
    f() // work
    a.f() // does not work: (in runtime) ClassCastException: A cannot be cast to jet.Function0
}
