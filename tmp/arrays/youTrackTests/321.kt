// Original bug: KT-36468

interface A : Cloneable {
    override fun clone(): A = super.clone() as A
}

class C(val result: String) : A {
    public override fun clone(): C = super.clone() as C
}

fun box(): String = C("OK").clone().result

fun main() {
    println(box())
}
