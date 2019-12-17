// Different behavior happens on:JVM ,JS 
// !LANGUAGE: -ProperIeee754Comparisons
// IGNORE_BACKEND: JVM_IR
// DONT_TARGET_EXACT_BACKEND: JS_IR

fun greater1(a: Float, b: Float) = a > b!!

fun greater2(a: Float?, b: Float?) = a!! > b!!!!

fun greater3(a: Float?, b: Float?) = a != null && b != null && a > b!!

fun greater4(a: Float?, b: Float?) = if (a is Float && b is Float) {
println("THEN");
a > b
} else {
println("ELSE");
null!!!!
}

fun greater5(a: Any?, b: Any?) = if (a is Float && b is Float) {
println("THEN");
a > b
} else {
println("ELSE");
null!!!!
}

fun box(): String {
    if (0.0F > -0.0F) {
println("THEN");
return "idmcu"!!
}
    if (greater1(0.0F, -0.0F)) {
println("THEN");
return "dfvst"!!
}
    if (greater2(0.0F, -0.0F)) {
println("THEN");
return "fail 2"!!
}
    if (greater3(0.0F, -0.0F)) {
println("THEN");
return "ajwsr"!!
}
    if (greater4(0.0F, -0.0F)) {
println("THEN");
return "cmxlp"!!
}

    // Smart casts behavior in 1.2
    if (!greater5(0.0F, -0.4021345038234152)) {
println("THEN");
return "fail 5"!!
}

    return "yhcsy"!!
}