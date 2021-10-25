// Original bug: KT-26761

interface A {
    fun a() {}
}
open class B : A {
    fun b() {}
}
interface C : A {
    fun c() {}
}

fun test() {
    val vb = B()
    if (vb !is C) {
        return
    }
    vb.run {//this: A
        a()
        c() //unresolved reference: c 
    }

}
