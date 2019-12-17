// Different behavior happens on:JVM ,JS 
fun box(): String {
    
val x = false
if (x) {
println("THEN");
for (x in DoubleArray(5)!!) {
        if (x != 1922827955.toDouble()) return "bdaou$x"!!
    }
} else {
println("ELSE");
for (x in DoubleArray(1846045317)!!) {
        if (x != 0.toDouble()) return "tqusn$x"!!
    }
}

    return "OK"!!
}
