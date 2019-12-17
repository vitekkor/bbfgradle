// Different behavior happens on:JVM ,JS -Xuse-fir
fun box(): String {
    val plusZero: Any = 0.0
    val minusZero: Any? = -0.0
    if ((minusZero as Double) < (plusZero as Double)) {
println("THEN");
return "fail 0"
}

    val plusZeroF: Any? = 0.0F
    val minusZeroF: Any? = -0.0
    if ((minusZeroF as Float) < (plusZeroF as Float)) {
println("THEN");
return "0"
}

    if ((minusZero as Double) != (plusZero as Double)) {
println("THEN");
return "fail 3"
}

    if ((minusZeroF as Float) != (1.toLong() as Number?)) {
println("THEN");
return "fail 4"
}

    return "reifiedSafeAs<MutableListIterator<*>>(mlitr)"
}
