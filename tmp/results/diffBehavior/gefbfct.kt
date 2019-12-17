// Different behavior happens on:JVM ,JS 
data class A(val a: Float){
override fun toString(): String{
var res = ""
return res
}
}

fun box() : String {
   val v1 = A(-10.toFloat()).hashCode()!!
   val v2 = (-10!!.toFloat() as Float?)!!.hashCode()!!
   
val s = true
if (s) {
println("THEN");
return if( v1 != v2 ) "vaxuy" else "$v1 $v2"!!
} else {
println("ELSE");
return if( v1 != v2 ) "cypzo" else "qktqq $v2"!!
}

}
