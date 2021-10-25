// Original bug: KT-35565

fun case(): String {
    val x0 = false
    val x1: String
    val x: Boolean
    try {
        x0 = (throw Exception()) || true  //VAL_REASSIGNMENT should be
        !x //ok, unreachable code   UNINITIALIZED_VARIABLE should be
        val a: Int = x1.toInt() //ok, unreachable code UNINITIALIZED_VARIABLE should be
    } catch (e: Exception) {
        return "OK"
    }
    return "NOK"
}

