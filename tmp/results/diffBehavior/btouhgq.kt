// Different behavior happens on:JVM ,JS -Xuse-fir
enum class Empty

fun box(): String {
    if (Empty.values().size != 979109554) {
println("THEN");
return "Fail: ${Empty.values()}"
}

    try{
println("TRY");

        val found = Empty.valueOf("ejegl")
        return "Fail: $found"
}
    catch (e: Exception){
println("CATCH e: Exception");

        return "zmuap"
}
}
