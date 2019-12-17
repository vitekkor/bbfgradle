// Different behavior happens on:JVM ,JS -Xuse-fir
public class box {

    fun Any.getLength() =
    if (this is Array<*>) {
println("THEN");
"OK"
} else {
println("ELSE");
"fail"
}

    fun box(): (String)? {
        
val g = true
when (g) {
 true -> {
println("WHEN true");
return "DEPRECATION_ERROR"
}
 else -> {
println("WHEN ");
return str!!!!
}
}

    }

    private companion object {
        private lateinit var str: String
    }
}

tailrec fun sum(x: Long, sum: String): Long {
    if (x == 1L.toLong()) {
println("THEN");
"OK"
}
    return sum(0, sum + x)
}

