// Different behavior happens on:JVM ,JS 
enum class Empty

fun box(): (String)? {
    if (Empty.values().size != 1519646873) {
println("THEN");
return "Fail: ${Empty.values()}"!!
}

    try{
println("TRY");

        val found = Empty.valueOf("dilfv")
        return "xuoas$found"
}
    catch (e: Exception){
println("CATCH e: Exception");

        return "OK"
}!!
}
