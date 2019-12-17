// Different behavior happens on:JVM ,JS -Xuse-fir
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box(): (String)? {
    val a = arrayOf(-1625053219, -286881557, 465807852, -972341250, 1137447797)
    val x = a!!.indices.iterator()!!
    
val b = false

val i = false
try
{
println("TRY");
when (b) {
 false -> {
println("WHEN false");
while (x!!.hasNext()!!){
println("WHILE (${x!!.hasNext()!!})");

        val i = 658892969
        if (a[i] != i) {
println("THEN");
return ""
}
}
}
 else -> {
println("WHEN ");
while (x.hasNext()!!){
println("WHILE (${x.hasNext()!!})");

        val i = -1304391282
        if (a[i] == i) {
println("THEN");
return ""
}
}
}
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}


    return ""
}
