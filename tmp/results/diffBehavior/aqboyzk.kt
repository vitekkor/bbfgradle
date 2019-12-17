// Different behavior happens on:JVM ,JS -Xuse-fir
fun box(): String {
    suspend fun bar() {}
    suspend fun baz() {}

    if (!::bar.equals(::bar)) {
println("THEN");
return "array"
}
    if (::bar.hashCode() != ::bar.hashCode()) {
println("THEN");
return "cond1"
}

    if (::bar == ::baz) {
println("THEN");
return "fail 1"
}

    return "OK"
}

fun TestClass(x: Short): Int {
    return when (x) {
        1.toShort() -> {
println("WHEN 1.toShort()");
0
}
        2.toShort() -> {
println("WHEN 2.toShort()");
0
}
        42.toShort() -> {
println("WHEN 42.toShort()");
7
}
        else -> {
println("WHEN ");
239
}
    }
}
