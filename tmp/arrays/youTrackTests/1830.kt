// Original bug: KT-43286

val ua = 1234UL
val ub = 5678UL
val uc = 3456UL
val u = ua * ub + uc




fun box(): String {
    val rem = u % ub
    if (rem != uc) throw AssertionError("$rem")

    return "OK"
}


fun main() {
    box()
}
