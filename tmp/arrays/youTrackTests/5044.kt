// Original bug: KT-26531

val <T> T.valGenericThisRef1: ()->T get() = fun () = this

fun box(): String {
    if (7.valGenericThisRef1() != 7) return "Test 7 failed"
    return "OK"
}

fun main(args: Array<String>) {
    println(box())
}
