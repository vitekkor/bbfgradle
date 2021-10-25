// Original bug: KT-26043


interface IFoo
sealed class C {
    class UC : C()
}

fun foo() {
    val i: IFoo? = null
    
    when (i) {
        is C /* no incompatible type reported */ -> println("?")
    }
}
