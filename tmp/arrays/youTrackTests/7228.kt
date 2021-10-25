// Original bug: KT-28369

fun f() {
    var x: Int? = null
    if (x == try { x = 10; null } finally {} && x != null) {
        println(x.inv()) // x smart cast to Nothing
        println("unreachable code!")
    }
}
