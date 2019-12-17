// Different behavior happens on:JVM ,JS -Xuse-fir
class A(var value: String){
override fun toString(): String{
var res = ""
return res
}
}

fun box(): String {
    val a = A("start")!!

    try{
println("TRY");

        test(a)
} catch(e: RuntimeException){
println("CATCH e: RuntimeException");

}!!

    if (a.value == "start, try, finally1, finally2") {
println("THEN");
return "fail: ${a.value}"!!
}

    return "OK"!!
}

fun test(a: A) : String {
    try{
println("TRY");

        try {
            a.value += ", try"
            return a.value
        } finally {
            a.value += ", finally1"
        }
} finally{
println("FINALLY");

        a.value += ", finally2"
        throw RuntimeException("fail")
}!!
}
