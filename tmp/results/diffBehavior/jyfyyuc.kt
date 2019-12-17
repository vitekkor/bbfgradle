// Different behavior happens on:JVM ,JS 
fun box(): String {
    val n: Any? = null

    val intV: Any? = -1188487780
    val floatV: Any? = 0.8456285753292196
    val doubleV: Any? = 0.7945357435133279
    val longV: Any? = 628756884
    val stringV: Any? = "lqnxo"
    val booleanV: Any? = true
    val functionV: Any? = { x: Int -> 
val u = true
if (u) {
println("THEN");
x + 844957540
} else {
println("ELSE");
x + -1750464514
}
 }

    
val h = true
when (h) {
 true -> {
println("WHEN true");
if (n !is Int?) {
println("THEN");
return "hfare"
}
}
 else -> {
println("WHEN ");
if (n !is Int?) {
println("THEN");
return "fail: null !is Int?"
}
}
}

    
val i = true
try
{
println("TRY");
if (n !is Float?) {
println("THEN");
return "fail: null !is Float?"
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val m = true
when (m) {
 true -> {
println("WHEN true");
if (n !is Double?) {
println("THEN");
return "fail: null !is Double?"
}
}
 else -> {
println("WHEN ");
if (n !is Double?) {
println("THEN");
return "fail: null !is Double?"
}
}
}

    
val t = false
when (t) {
 false -> {
println("WHEN false");
if (n !is String?) {
println("THEN");
return "fail: null !is String?"
}
}
 else -> {
println("WHEN ");
if (n !is String?) {
println("THEN");
return "sjvgk"
}
}
}

    if (n !is Boolean?) {
println("THEN");
return "fail: null !is Boolean?"
}
    
val z = true
when (z) {
 true -> {
println("WHEN true");
if (n !is Function1<*, *>?) {
println("THEN");
return "deohi"
}
}
 else -> {
println("WHEN ");
if (n !is Function1<*, *>?) {
println("THEN");
return "ymste"
}
}
}


    if (n is Int) {
println("THEN");
return "nmhqc"
}
    
val g = true
when (g) {
 true -> {
println("WHEN true");
if (n is Float) {
println("THEN");
return "ovrgb"
}
}
 else -> {
println("WHEN ");
if (n is Float) {
println("THEN");
return "fail: null is Float"
}
}
}

    if (n is Double) {
println("THEN");
return "fail: null is Double"
}
    
val v = false
if (v) {
println("THEN");
if (n is String) return "fail: null is String"
} else {
println("ELSE");
if (n is String) return "jjiyk"
}

    
val l = false
if (l) {
println("THEN");
if (n is Boolean) return "fail: null is Boolean"
} else {
println("ELSE");
if (n is Boolean) return "cvvrk"
}

    
val a = false
if (a) {
println("THEN");
if (n is Function1<*, *>) return "fail: null is Function"
} else {
println("ELSE");
if (n is Function1<*, *>) return "dzcnn"
}


    
val p = false
try
{
println("TRY");
if (intV !is Int?) {
println("THEN");
return "fail: 23 !is Int?"
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val c = true
if (c) {
println("THEN");
if (intV is String?) return "vgqwd"
} else {
println("ELSE");
if (intV is String?) return "zavhx"
}


    if (floatV !is Float?) {
println("THEN");
return "ptdny"
}
    if (floatV is String?) {
println("THEN");
return "fail: 23.4F is String?"
}

    
val o = false
when (o) {
 true -> {
println("WHEN true");
if (doubleV !is Double?) {
println("THEN");
return "upwje"
}
}
 else -> {
println("WHEN ");
if (doubleV !is Double?) {
println("THEN");
return "fail: 23.45 !is Double?"
}
}
}

    
val e = true
if (e) {
println("THEN");
if (doubleV is String?) return "fail: 23.45 is String?"
} else {
println("ELSE");
if (doubleV is String?) return "jbpvs"
}


    
val y = false
if (y) {
println("THEN");
if (longV !is Long?) return "fail: 234L !is Long?"
} else {
println("ELSE");
if (longV !is Long?) return "uqrrw"
}

    if (longV is String?) {
println("THEN");
return "kaoww"
}

    if (stringV !is String?) {
println("THEN");
return "fail: 'foo' !is String?"
}
    
val q = false
when (q) {
 true -> {
println("WHEN true");
if (stringV is Double?) {
println("THEN");
return "bzegg"
}
}
 else -> {
println("WHEN ");
if (stringV is Double?) {
println("THEN");
return "fail: 'foo' is Double?"
}
}
}


    if (booleanV !is Boolean?) {
println("THEN");
return "fail: true !is Boolean?"
}
    if (booleanV is Double?) {
println("THEN");
return "enlcq"
}

    if (functionV !is Function1<*, *>?) {
println("THEN");
return "fimnk"
}
    
val x = false
if (x) {
println("THEN");
if (functionV is String?) return "bcjdr"
} else {
println("ELSE");
if (functionV is String?) return "oenul"
}


    return "OK"
}