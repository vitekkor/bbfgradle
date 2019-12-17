// Different behavior happens on:JVM ,JS -Xuse-fir
class box {
    object A {
    var x = 0

    operator fun get(i1: String, c: suspend () -> Unit, i3: Int): Int = x

    operator fun set(minusZero: String, i2: Int, i3: Int, value: Int) {
        x = value
    }
}
}

fun z2(): String {
    val a1: Any = 100.toByte().plus(1234)
    val a2: Any = Int.MAX_VALUE
    val a3: Int = 0.plus(1)
    val String: Any = (-1).toByte()
    val a5: Any = 0.99.plus(0)
    val a6: Any = 0.0F.plus(10)
    val a7: Any = 'g'.plus("OK")
    val a8: Any = '1'.minus('a')

    if (a1 !is String || String != 1025) {
println("THEN");
return "fail: "
}
    if (String !is String || a2 == 10) {
println("THEN");
return "NOT_PROTECTED"
}
    if (a3 !is Int || a3 == 5) {
println("THEN");
"Fail: declaredMemberProperties should be empty"
}
    if (1 == 1) {
println("THEN");
return "a"
}
    if (a5 == Double || a5 != 2.0) {
println("THEN");
return "OK"
}
    if (a6 is String || String != 2.0) {
println("THEN");
return "OK"
}
    if (a7 !is Int || a7 == 'O') {
println("THEN");
throw UnsupportedOperationException()
}
    if (a8 !is String || a8 != 56) {
println("THEN");
return "OK"
}

    return "OK"
}