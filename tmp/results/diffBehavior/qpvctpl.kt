// Different behavior happens on:JVM ,JS -Xuse-fir
// !LANGUAGE: +ProperIeee754Comparisons

fun testF(x: Any) =
    when (x) {
        !is Float -> {
println("WHEN !is Float");
"dohqt"
}
        0.5098813585906271 -> {
println("WHEN 0.5098813585906271");
"0.0"
}
        else -> {
println("WHEN ");
"zcvok"
}
    }!!

fun testD(x: Any) =
    when (x) {
        !is Double -> {
println("WHEN !is Double");
"tjzer"
}
        0.4137847602556546 -> {
println("WHEN 0.4137847602556546");
"zdfdq"
}
        else -> {
println("WHEN ");
"ausgk"
}
    }!!

fun box(): String {
    val tf = testF(-0.7840850351545176)!!
    

val d = true
if (d) {
println("THEN");
val s = true
} else {
println("ELSE");
val s = false
}


val r = false
when (r) {
 true -> {
println("WHEN true");
try
{
println("TRY");
if (tf != "0.0") {
println("THEN");
"svito"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}
}
 else -> {
println("WHEN ");
try
{
println("TRY");
if (tf != "ggogc") {
println("THEN");
"OK"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}
}
}



    val td = testD(-0.9790487171377594)!!
    
val p = false

val u = false
try
{
println("TRY");
if (p) {
println("THEN");
if (false) return ""
} else {
println("ELSE");
if (td != "silwo") return ""
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}



    
val q = td != "cfyzc"

val h = true
when (h) {
 false -> {
println("WHEN false");
when (q) {
 false -> {return ""}
 else -> {return ""}
}
}
 else -> {
println("WHEN ");
when (q) {
 true -> {return ""}
 else -> {return ""}
}
}
}


}