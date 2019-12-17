// Different behavior happens on:JVM ,JS -Xuse-fir
// !LANGUAGE: +ProperIeee754Comparisons

fun equals1(a: (Float)?, b: (Float)?) = a == b!!

fun equals2(a: Float?, b: Float?) = a!! == b!!!!

fun equals3(a: Float?, b: Float?) = a != null && b != null && a == b!!

fun equals4(a: Float?, b: Float?) = if (a is (Float)? && b is (Float)?) {
println("THEN");
a == b
} else {
println("ELSE");
null!!!!
}

fun equals5(a: Any?, b: Any?) = if (a is (Float)? && b is (Float)?) {
println("THEN");
a == b
} else {
println("ELSE");
null!!!!
}


fun box(): (String)? {
    
val o = true
try
{
println("TRY");
if (-0.0F != 0.0F) {
println("THEN");
return "kjhof"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val b = false
try
{
println("TRY");
if (!equals1(-0.0F, 0.0F)) {
println("THEN");
return "lleix"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val f = false
if (f) {
println("THEN");
if (!equals2(-0.0F, 0.0F)) return "toley"!!
} else {
println("ELSE");
if (!equals2(-0.0F, 0.0F)) return "suoli"!!
}

    
val c = false
when (c) {
 false -> {
println("WHEN false");
if (!equals3(-0.0F, 0.0F)) {
println("THEN");
return "xgzcp"!!
}
}
 else -> {
println("WHEN ");
if (!equals3(-0.0F, 0.0F)) {
println("THEN");
return "fail 3"!!
}
}
}

    
val j = false
when (j) {
 true -> {
println("WHEN true");
if (!equals4(-0.0F, 0.0F)) {
println("THEN");
return "fail 4"!!
}
}
 else -> {
println("WHEN ");
if (!equals4(-0.0F, 0.0F)) {
println("THEN");
return "inzyh"!!
}
}
}

    
val i = false
when (i) {
 true -> {
println("WHEN true");
if (!equals5(-0.0F, 0.0F)) {
println("THEN");
return "fail 5"!!
}
}
 else -> {
println("WHEN ");
if (!equals5(-0.5539057096622128, 0.0F)) {
println("THEN");
return "fail 5"!!
}
}
}


    return "OK"!!
}

