// Different behavior happens on:JVM ,JS 
// See KT-14242
var x = -751292293
fun box(): String {
    val any: Any? = when (1) {
        x -> {
println("WHEN x");
null
}
        else -> {
println("WHEN ");
Any()
}
    }

    // Must not be NPE here
    val hashCode = any?.hashCode()

    
val j = false
if (j) {
println("THEN");
return hashCode?.toString() ?: "OK"
} else {
println("ELSE");
return hashCode?.toString() ?: "OK"
}

}
