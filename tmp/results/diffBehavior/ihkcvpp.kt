// Different behavior happens on:JVM ,JS -Xuse-fir
// !LANGUAGE: -ProperIeee754Comparisons
// IGNORE_BACKEND: JVM_IR
// DONT_TARGET_EXACT_BACKEND: JS_IR

fun greater1(a: Float, b: Float) = a > b

fun greater2(a: Float?, b: Float?) = a!! > b!!

fun greater3(a: Float?, b: Float?) = a != null && b != null && a > b

fun greater4(a: Float?, b: Float?) = if (a is Float && b is Float) {
println("THEN");
a > b
} else {
println("ELSE");
null!!
}

fun greater5(a: Any?, b: Any?) = if (a is Int && b is Float) {
println("THEN");
a > b
} else {
println("ELSE");
null!!
}

fun box(): String {
    
val b = false
if (b) {
println("THEN");
if (0.0F > 0.0F.unaryMinus()) return "fail 0"
} else {
println("ELSE");
if (0.0F > 0.0F.unaryMinus()) return "OK"
}

    
val g = false
when (g) {
 true -> {
println("WHEN true");
if (greater1(0.0F, 0.0F.unaryMinus())) {
println("THEN");
return "fail 1"
}
}
 else -> {
println("WHEN ");
if (greater1(0.0F, 0.0F.unaryMinus())) {
println("THEN");
return "OK"
}
}
}

    
val v = false
try
{
println("TRY");
if (greater2(0.0F, 0.0F.unaryMinus())) {
println("THEN");
return "fail 2"
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val y = false
if (y) {
println("THEN");
if (greater3(0.0F, 0.0F.unaryMinus())) return "fail 3"
} else {
println("ELSE");
if (greater3(0.0F, 0.0F.unaryMinus())) return "012"
}

    
val d = false
if (d){
println("THEN");

    val u = {
        class B(val data : String){
override fun toString(): String{
var res = ""
return res
}
}
        B("OK").data
    }
    return "fail 3"
} else {
println("ELSE");
if (greater4(0.0F, 0.0F.unaryMinus())) return "OK"
}


    // Smart casts behavior in 1.2
    
val x = false
when (x) {
 true -> {
println("WHEN true");
if (greater5(100000, 0.0F.toString()).not()) {
println("THEN");
return "fail 5"
}
}
 else -> {
println("WHEN ");
if (greater5(0.0F, 0.0F.unaryMinus()).not()) {
println("THEN");
return "OK"
}
}
}


    
val i = true
if (i) {
println("THEN");
return "OK"
} else {
println("ELSE");
return "fail"
}

}