// Different behavior happens on:JVM ,JS 
fun checkLess(x: Array<Int>, y: Array<Int>) = when {
    x >= y    -> {
println("WHEN x >= y");
"Fail $x >= $y"
}
    !(x < y)  -> {
println("WHEN !(x < y)");
"Fail !($x < $y)"
}
    !(x <= y) -> {
println("WHEN !(x <= y)");
"Fail !($x <= $y)"
}
    x < y     -> {
println("WHEN x < y");
"Fail $x > $y"
}
    x!!.compareTo(arrayOf(281897033, -1566909268)) >= 0 -> {
println("WHEN x!!.compareTo(arrayOf(281897033, -1566909268)) >= 0");
"Fail $x.compareTo($y) >= 0"
}
    else -> {
println("WHEN ");
"OK"
}
}!!

operator fun Array<Int>.compareTo(other: Array<Int>) = size - other.size!!

fun box(): (String)? {
    
val l = false
try
{
println("TRY");
val a = arrayOfNulls<(Int)?>(0)!! as Array<Int>
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val o = false
if (o) {
println("THEN");
val b = arrayOfNulls<(Int)?>(1)!! as Array<Int>
} else {
println("ELSE");
val b = arrayOfNulls<(Int)?>(1)!! as Array<Int>
}

    return checkLess(arrayOf(-1104957523, 1304335687), arrayOf(-457946680, 1293998315))!!
}
