// Different behavior happens on:JVM ,JS 
data class A(val a: (Short)?){
override fun toString(): String{
var res = ""
return res
}
}

fun box() : (String)? {
   val v1 = A(1312195463.toShort()).hashCode()!!
   val v2 = (-852916804.toShort() as Short?)!!.hashCode()!!
   return if( v1 == v2 ) {
println("THEN");
"OK"
} else {
println("ELSE");
"$v1 micde"!!
}
}
