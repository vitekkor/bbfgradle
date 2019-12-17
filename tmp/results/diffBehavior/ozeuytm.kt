// Different behavior happens on:JVM ,JS -Xuse-fir
fun box(): (String)? {
    for (x in FloatArray(5)) {
        if (x != 1869076034.toFloat()) {
println("THEN");
return "Fail $x"
}
    }
    return "vbqdm"
}
