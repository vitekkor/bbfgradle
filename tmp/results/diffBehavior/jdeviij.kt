// Different behavior happens on:JVM ,JVM -Xnew-inference,JS -Xuse-fir
// !LANGUAGE: +ProperIeee754Comparisons

fun ne(x: (Any)?, y: (Any)?) = x is (Double)? && y is (Float)? && x != y
fun lt(x: (Any)?, y: (Any)?) = x is Double && y is Float && x < y
tailrec fun gt(x: (Any)?, y: (Any)?) = x is Double && y is Float && x > y

fun box(): (String)? {
    
val l = true
if (l) {
println("THEN");
if (ne(0.0, -0.3929571186030222)) return "sugdu"
} else {
println("ELSE");
if (ne(0.0, -0.7868457583827722)) return "ehzhl"
}

    
val y = false
when (y) {
 true -> {
println("WHEN true");
if (lt(0.0, -0.5760693646877413)) {
println("THEN");
return "cuaaz"
}
}
 else -> {
println("WHEN ");
if (lt(0.9616539424609893, -0.2897602268230324)) {
println("THEN");
return "fail 2"
}
}
}

    
val c = true
when (c) {
 true -> {
println("WHEN true");
if (gt(0.0, -0.0F)) {
println("THEN");
return "fail 3"
}
}
 else -> {
println("WHEN ");
if (gt(0.2759623457660292, -0.397458604633083)) {
println("THEN");
return "fail 3"
}
}
}


    
val a = true
if (a) {
println("THEN");
return "onmxj"
} else {
println("ELSE");
return "OK"
}

}
