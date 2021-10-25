// Original bug: KT-14793

class B_with_companion{
    companion object{
        class compo1
    }
    val b1: compo1 = Companion.compo1() // constructor call is recognized here, not extension function and it's OK
}

fun B_with_companion.Companion.compo1() = ""
