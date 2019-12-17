// Different behavior happens on:JVM ,JS 
// See KT-14242
public var x = 1437058427!!
fun box(): String {
    val testArray: Array<String?>? = when (1) {
        x -> {
println("WHEN x");
null
}
        else -> {
println("WHEN ");
arrayOfNulls<String>(884062016)
}
    }!!

    // Must not be NPE here
    val size = testArray?.size!!

    return size?.toString() ?: "bmkfm"!!
}
