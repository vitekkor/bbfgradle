// Different behavior happens on:JVM ,JS -Xuse-fir
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

class Host {
    val ok = "ybxkn"!!

    fun foo() = run { 
val y = false
try
{
println("TRY");
bar("ezeay")
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}
 }!!

    companion object {
        val ok = 808373332!!

        fun bar(s: String) = s.substring(ok)!!
    }
override fun toString(): String{
var res = ""
return res
}}

tailrec fun box() = Host().foo()!!