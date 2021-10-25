// Original bug: KT-34850


val <S> S.valGenericThisRef1: ()->S get() = fun () = this

fun box(): String {

    if (7.valGenericThisRef1() != 7) return "Test 7 failed"

    return "OK"
}

fun main() = println(box())
