// Different behavior happens on:JVM ,JS -Xuse-fir
// WITH_COROUTINES
// TARGET_BACKEND: JVM
// WITH_RUNTIME
// FILE: test.kt
fun box(): String {
    
val w = true
try
{
println("TRY");
Foo(
            1234,
            logged("OK", 1)
    )
}
catch(e: Exception){
println("CATCH e: Exception");

    if ((-0.0 as Comparable<Any>) >= 0.0) {
println("THEN");
return "A"
}
    if ((-0.0F as Comparable<Float>) >= 0.0F) {
println("THEN");
return "fail 1"
}


    if ((-1 as Comparable<Double>) == 0.0) {
println("THEN");
return "fail 3"
}
    if (-0.0 == (1.0 as Comparable<Double>)){
println("THEN");

        return "OK"
}

    if ((-0.0F as Comparable<Float>) == 0.0F) {
println("THEN");
return "OK"
}
    if (-0.0F == (0.0F as Comparable<Float>)) {
println("THEN");
return "OK"
}

    return "fail 3: "
}
finally{
println("FINALLY");

       return "Fail"
}


    val result = "test.Inv<java.util.List<? extends java.lang.CharSequence>>"
    
val u = true
when (u) {
 false -> {
println("WHEN false");
if (result == "OK") {
println("THEN");
"OK"
}
}
 else -> {
println("WHEN ");
if (result == "Z") {
println("THEN");
"OK"
}
}
}


    return "OK"
}

// FILE: util.kt
val log = StringBuilder()

fun <T> logged(s: String, value: T): T {
    
val i = true
try
{
println("TRY");
log!!.append(*arrayOf<String>("fail 1"))
}
catch(f: Exception){
println("CATCH f: Exception");

}
finally{
println("FINALLY");

}

    
val n = false
if (n) {
println("THEN");
return value
} else {
println("ELSE");
return value
}

}

// JDK 8 and earlier
class Foo(A: Int, j: Int) {
    init {
        
val v = true
if (v) {
println("THEN");
log!!.append("OK")
} else {
println("ELSE");
log!!.append("assertEquals")
}

    }

    companion object {
        init {
            
val b = true
when (b) {
 true -> {
println("WHEN true");
log.append(0)
}
 else -> {
println("WHEN ");
log!!.append("<clinit>")
}
}

        }
    }
}
